package Extra.Ejercicio7E;

import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio7E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/resources/classicmodels.properties");
            try (Connection connection = establecerConexion(properties)) {
                String ciudad = "";
                do {
                    ciudad = MiEntradaSalida.solicitarCadena("Introduce la ciudad de los" +
                            " empleados a mostrar");
                } while (!mostrarEmpleadosCiudad(ciudad, connection));
            }
        } catch (SQLException | Ejercicio7EException | InvalidPathException e) {
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
     * @throws Ejercicio7EException
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio7EException {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio7EException(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar todos los empleados que
     * pertenecen a la ciudad pasada por parámetros
     *
     * @param ciudad     la ciudad de donde son los empleados
     * @param connection la conexión a la base de datos
     * @return devuelve tre si hay empleados en la ciudad especificada, si no false
     * @throws Ejercicio7EException
     */
    public static boolean mostrarEmpleadosCiudad(String ciudad, Connection connection) throws Ejercicio7EException {
        try {
            PreparedStatement ps = connection.prepareStatement("select customerName from customers" +
                    " where city like ?");
            ps.setString(1, ciudad);
            ResultSet rs = ps.executeQuery();
            boolean alMenosUno = false;
            while (rs.next()) {
                alMenosUno = true;
                System.out.println("Cliente: " + rs.getString(1));
            }
            return alMenosUno;

        } catch (SQLException e) {
            throw new Ejercicio7EException(e.getMessage());
        }
    }
}