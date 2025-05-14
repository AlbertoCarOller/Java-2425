package Boletin1.Ejercicio5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio5 {
    public static void main(String[] args) {
        try {
            System.out.println("Proveedor: " + obtenerProducto("Min Lin Diecast"));

        } catch (Ejercicio5Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a devolver el nombre del producto que tiene
     * como proveedor el nombre pasado por parámetros
     *
     * @param nombreProveedor es el nombre del proveedor del producto
     * @return el nombre del producto con el proveedor especificado
     * @throws Ejercicio5Exception
     */
    public static String obtenerProducto(String nombreProveedor) throws Ejercicio5Exception {
        Properties properties = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(Path.of(
                "Boletin_Tema7/src/main/java/Boletin1/Ejercicio5/ejercicio5.properties").toFile()))) {
            properties.load(br);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                PreparedStatement ps = connection.prepareStatement("select * from products where" +
                        " productVendor LIKE (?)");
                ps.setString(1, nombreProveedor);
                ResultSet rs = ps.executeQuery();
                // Antes de poder obtener un campo del ResultSet, se debe llamar a .next() para comprobar que hay campos
                if (rs.next()) {
                    return rs.getString("productName");
                }
            }

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio5Exception(e.getMessage());
        }
        return "No hay coincidencias";
    }
}