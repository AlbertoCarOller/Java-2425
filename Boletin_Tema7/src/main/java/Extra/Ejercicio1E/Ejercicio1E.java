package Extra.Ejercicio1E;

import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio1E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/java/Extra/Ejercicio1E/ejercicio1E.properties");
            try (Connection connection = establecerConexion(properties)) {
                String ciudad = MiEntradaSalida.solicitarCadena("Introduce la ciudad");
                obtenerOficina(ciudad, connection);
            }
        } catch (Ejercicio1EException | SQLException | InvalidPathException e) {
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
     * @throws Ejercicio1EException
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio1EException {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio1EException(e.getMessage());
        }
    }

    /**
     * Este método va a imprimir por pantalla el código de la oficina
     * y el teléfono de la misma
     * @param ciudad la ciudad a la que pertenece la oficina
     * @param connection la conexión a la base de datos
     * @throws Ejercicio1EException
     */
    public static void obtenerOficina(String ciudad, Connection connection) throws Ejercicio1EException {
        try {
            PreparedStatement ps = connection.prepareStatement("select officeCode, phone from offices " +
                    "where city like ?");
            ps.setString(1, ciudad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Código oficina: ".concat(rs.getString(1)).concat(", Teléfono: ")
                        .concat(rs.getString(2)));
            }

        } catch (SQLException e) {
            throw new Ejercicio1EException(e.getMessage());
        }
    }
}