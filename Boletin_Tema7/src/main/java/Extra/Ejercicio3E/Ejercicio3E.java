package Extra.Ejercicio3E;

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

public class Ejercicio3E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/java/Extra/Ejercicio3E/ejercicio3E.properties");
            try (Connection connection = establecerConexion(properties)) {
                int cantidad = MiEntradaSalida.solicitarEntero("Introduce la cantidad a sumar:");
                String categoria = MiEntradaSalida.solicitarCadena("Introduce la categoría:");
                actualizarStock(connection, cantidad, categoria);
            }
        } catch (SQLException | Ejercicio3EException | InvalidPathException e) {
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
     * @throws Ejercicio3EException
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio3EException {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio3EException(e.getMessage());
        }
    }

    /**
     * Este método va a actualizar la cantidad de stock de
     * todos los productos de la categoría específica
     *
     * @param connection la conexión a la base de datos
     * @param cantidad   la cantidad a sumar
     * @param categoria  la categoría a sumar el stock
     * @throws Ejercicio3EException
     */
    public static void actualizarStock(Connection connection, int cantidad, String categoria) throws Ejercicio3EException {
        try {
            PreparedStatement ps = connection.prepareStatement("update products set quantityInStock" +
                    " = quantityInStock + ? where productLine like ?");
            ps.setInt(1, cantidad);
            ps.setString(2, categoria);
            System.out.println("Filas actualizadas: " + ps.executeUpdate());

        } catch (SQLException e) {
            throw new Ejercicio3EException(e.getMessage());
        }
    }
}