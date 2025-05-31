package Boletin1.Ejercicio7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio7 {
    public static void main(String[] args) {
        try {
            //insetarCategoriasAleatorias();
            insetarCategoriasAleatoriasV2();

        } catch (Ejercicio7Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a intentar generar 1000000 de categorías
     * con el formato especificado y se van a insertar mediante
     * 'PreparedStatement'
     *
     * @throws Ejercicio7Exception
     */
    public static void insetarCategoriasAleatorias() throws Ejercicio7Exception {
        Properties properties = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(Path.of(
                "Boletin_Tema7/src/main/resources/classicmodels.properties").toFile()))) {
            properties.load(br);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                // Se van a intentar crear 1000000 de Strings para posteriormente insertarlos
                long inicio = System.currentTimeMillis();
                for (int i = 0; i < 50; i++) {
                    String nombreCategoria = "RANDOM_";
                    // Se generan 8 números de los que va seguido el 'RANDOM_'
                    for (int j = 0; j < 8; j++) {
                        nombreCategoria = nombreCategoria.concat(String.valueOf((int) (Math.random() * 9) + 1));
                    }
                    PreparedStatement ps = connection.prepareStatement("Insert into productlines" +
                            " (productLine, textDescription) values ((?), (?))");
                    ps.setString(1, nombreCategoria);
                    ps.setString(2, String.valueOf(i));
                }
                long fin = System.currentTimeMillis();
                System.out.println((fin - inicio) + " milisegundos");
            }

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio7Exception(e.getMessage());
        }
    }

    /**
     * Este método va a intentar generar 1000000 de categorías
     * con el formato especificado y se van a insertar mediante
     * 'Statement'
     *
     * @throws Ejercicio7Exception
     */
    public static void insetarCategoriasAleatoriasV2() throws Ejercicio7Exception {
        Properties properties = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(Path.of(
                "Boletin_Tema7/src/main/java/Boletin1/Ejercicio7/ejercicio7.properties").toFile()))) {
            properties.load(br);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                // Se van a intentar crear 50 de Strings para posteriormente insertarlos
                long inicio = System.currentTimeMillis();
                for (int i = 0; i < 50; i++) {
                    String nombreCategoria = "RANDOM_";
                    // Se generan 8 números de los que va seguido el 'RANDOM_'
                    for (int j = 0; j < 8; j++) {
                        nombreCategoria = nombreCategoria.concat(String.valueOf((int) (Math.random() * 9) + 1));
                    }
                    Statement st = connection.createStatement();
                    st.executeUpdate("Insert into productlines (productLine, textDescription) values ("
                            + "'" + nombreCategoria + "'" + ", " + "'" +  i + "'" + ")");
                }
                long fin = System.currentTimeMillis();
                System.out.println((fin - inicio) + " milisegundos");
            }

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio7Exception(e.getMessage());
        }
    }
}