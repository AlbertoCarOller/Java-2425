package Boletin1.Ejercicio4;

import Boletin1.Ejercicio3.Ejercicio3Exception;
import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio4 {
    public static void main(String[] args) {
        try {
            // Solicitamos los datos
            int precio = MiEntradaSalida.solicitarEntero("Elija el precio");
            char primeraLetra = MiEntradaSalida.solicitarCaracter("Elija la primera letra");
            mostrarProductosMinusculaValorLetra(precio, primeraLetra);

        } catch (Ejercicio4Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar los productos con un precio menor al pasado por
     * parámetros y que empiece por la letra pasada por parámetros, se
     * mostrará el nombre del producto en minúsculas
     *
     * @param precio       el precio del que tiene que ser menor el producto
     * @param primeraLetra la letra por la que debe empezar el nombre del producto
     * @throws Ejercicio3Exception
     */
    public static void mostrarProductosMinusculaValorLetra(int precio, char primeraLetra) throws Ejercicio4Exception {
        Properties properties = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(Path.of(
                "Boletin_Tema7/src/main/resources/classicmodels.properties").toFile()))) {
            properties.load(br);
            String url = properties.getProperty("db.url");
            String usuario = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            try (Connection connection = DriverManager.getConnection(url, usuario, password)) {
                PreparedStatement ps = connection.prepareStatement("select LOWER(productName) from products where buyPrice < (?)" +
                        " AND productName LIKE (?)");
                // Le pasamos el valor del precio
                ps.setInt(1, precio);
                /* Le pasamos el valor de la primera letra, IMPORTANTE el '%' dentro de la sentencia
                 * SQL es interpretado como una sentencia SQL literal, no como parte del valor o de
                 * la palabra en este caso, así que se le tiene que pasar como parámetros */
                ps.setString(2, primeraLetra + "%");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    /* Mostramos el nombre de los productos en minúsculas, cogemos 1,
                     * porque se selecciona una sola columna que es 'productName' */
                    System.out.println(rs.getString(1));
                }
            }

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio4Exception(e.getMessage());
        }
    }
}