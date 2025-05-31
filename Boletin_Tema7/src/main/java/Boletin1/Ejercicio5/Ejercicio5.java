package Boletin1.Ejercicio5;

import Boletin1.Ejercicio6.Ejercicio6Exception;

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
            System.out.println("Proveedor: " + obtenerProveedor("1952 Alpine Renault 1300"));

        } catch (Ejercicio5Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a buscar por el nombre del producto su respectivo
     * proveedor
     * @param nombreProducto el nombre del producto
     * @return el nombre del proveedor
     * @throws Ejercicio6Exception
     */
    public static String obtenerProveedor(String nombreProducto) throws Ejercicio5Exception {
        Properties properties = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(Path.of(
                "Boletin_Tema7/src/main/resources/classicmodels.properties").toFile()))) {
            properties.load(br);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                PreparedStatement ps = connection.prepareStatement("select productVendor from products" +
                        " where productName LIKE (?)");
                ps.setString(1, nombreProducto);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getString(1);
                }
            }

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio5Exception(e.getMessage());
        }
        return "No hay coincidencias";
    }
}