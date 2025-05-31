package Boletin1.Ejercicio8;

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

public class Ejercicio8 {
    public static void main(String[] args) {
        try {
            eliminarCategoriasRandom();

        } catch (Ejercicio8Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a eliminar las categorías random, creadas en el
     * ejercicio anterior a este
     * @throws Ejercicio8Exception
     */
    public static void eliminarCategoriasRandom() throws Ejercicio8Exception {
        Properties properties = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(
                Path.of("Boletin_Tema7/src/main/resources/classicmodels.properties").toFile()))) {
            properties.load(br);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                PreparedStatement ps = connection.prepareStatement("delete from productlines where" +
                        " productLine LIKE (?)");
                ps.setString(1, "RANDOM_%");
                long inicio = System.currentTimeMillis();
                ps.executeUpdate();
                long fin = System.currentTimeMillis();
                System.out.println((fin - inicio) / 1000 + " segundos");
            }

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio8Exception(e.getMessage());
        }
    }
}