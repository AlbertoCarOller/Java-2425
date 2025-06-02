package Extra.Ejercicio8E;

import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio8E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/resources/classicmodels.properties");
            try (Connection connection = establecerConexion(properties)) {
                String pais = MiEntradaSalida.solicitarCadena("Introduce el país de los clientes a mostrar");
                mostrarClientesDePais(pais, connection);
            }
        } catch (SQLException | InvalidPathException | Ejercicio8EException e) {
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
     * @throws Ejercicio8EException
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio8EException {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio8EException(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar por pantalla los nombres y ciudad
     * de los clientes que sean del país especificado
     *
     * @param pais       país de los clientes a mostrar
     * @param connection la conexión con la base de datos
     * @throws Ejercicio8EException
     */
    public static void mostrarClientesDePais(String pais, Connection connection) throws Ejercicio8EException {
        try {
            PreparedStatement ps = connection.prepareStatement("select customerName, city from " +
                    "customers where country like ?");
            ps.setString(1, pais);
            ResultSet rs = ps.executeQuery();
            boolean paisEncontrado = false;
            while (rs.next()) {
                paisEncontrado = true;
                System.out.println("Cliente: " + rs.getString(1) + ", Ciudad: " + rs.getString(2));
            }
            if (!paisEncontrado) {
                throw new Ejercicio8EException("No se encuentra el país");
            }

        } catch (SQLException e) {
            throw new Ejercicio8EException(e.getMessage());
        }
    }
}