package Extra.Ejercicio9E;

import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Ejercicio9E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/resources/classicmodels.properties");
            try (Connection connection = establecerConexion(properties)) {
                String productCode = MiEntradaSalida.solicitarCadena("Introduce el código del producto a actualizar");
                double msrp = MiEntradaSalida.solicitarDoublePositivo("Introduce el nuevo precio de venta");
                actualizarPrecioVenta(productCode, msrp, connection);
            }

        } catch (InvalidPathException | SQLException | Ejercicio9EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a devolver una conexión, a partir
     * de un fichero .properties, del cual va a obtener
     * los datos de la conexión
     *
     * @param properties el fichero .properties
     * @return la conexión
     * @throws Ejercicio9EException
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio9EException {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio9EException(e.getMessage());
        }
    }

    /**
     * Este método va a actualizar el precio de venta del
     * producto con el código especificado por parámetros
     *
     * @param productCode el código del producto a actualizar
     * @param nuevoPrecio el precio actualizado del producto
     * @param connection  la conexión con la base de datos
     * @throws Ejercicio9EException
     */
    public static void actualizarPrecioVenta(String productCode, double nuevoPrecio, Connection connection)
            throws Ejercicio9EException {
        try {
            PreparedStatement ps = connection.prepareStatement("update products set MSRP = ? where" +
                    " productCode like ?");
            ps.setDouble(1, nuevoPrecio);
            ps.setString(2, productCode);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new Ejercicio9EException(e.getMessage());
        }
    }
}