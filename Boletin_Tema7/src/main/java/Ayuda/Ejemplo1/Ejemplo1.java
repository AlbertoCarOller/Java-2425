package Ayuda.Ejemplo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejemplo1 {
    public static void main(String[] args) {
        /* Creamos un Properties que nos va a permitir el contenido del fichero .properties
        * a este objeto y acceder a su información, a los values más concretamente que obtendremos
        * a partir del nombre del Key, ya que los .properties guardan información clave-valor */
        Properties properties = new Properties();
        // Con el BufferedReader accedemos al contenido del fichero .properties
        try (BufferedReader br = new BufferedReader(new FileReader(Path.of(
                "Boletin_Tema7/src/main/java/Ayuda/Ejemplo1/ejemplo1.properties").toFile()))) {
            // Cargamos el contenido del fichero, en el Properties, ahora podemos acceder a su contenido
            properties.load(br);
            // Accedemos a los valores de las diferentes keys guardadas en el fichero
            String cadenaConexion = properties.getProperty("db.url");
            String usuario = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            /* Creamos la conexión, nos conectamos mediante el usuario root, los datos del usuario, es decir
             * la contraseña, el nombre de usuario y la conexión se suelen guardar en un archivo .properties */
            try (Connection connection = DriverManager.getConnection(cadenaConexion, usuario, password);
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from customers where customerNumber = ?")) {
                // El 1 corresponde a la primera ? que hay, el 141 aquí se refiere a 'customerNumber'
                preparedStatement.setInt(1, 141);
                // Ejecutamos la consulta
                ResultSet rs = preparedStatement.executeQuery();
                // Mientras haya una siguiente fila se repite
                while (rs.next()) {
                    // Muestra el nombre del customer por consola
                    System.out.println(rs.getString("customerName"));
                }
                // Ahora probamos con Statement directamente
                String cat = "Classic cars"; // -> Esta es la línea de productos que se quieren buscar
                Statement statement = connection.createStatement();
                ResultSet rs1 = statement.executeQuery("select * from products where productLine = '" + cat + "'");
                while (rs1.next()) {
                    System.out.println(rs1.getString("productName"));
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (InvalidPathException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}