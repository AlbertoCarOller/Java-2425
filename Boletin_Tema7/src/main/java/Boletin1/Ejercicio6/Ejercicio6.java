package Boletin1.Ejercicio6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio6 {
    public static void main(String[] args) {
        try {
            System.out.println(obtenerVentas("Leslie"));

        } catch (Ejercicio6Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a devolver un string con los nombres de los customers junto con
     * el monto total de todas sus ventas
     *
     * @param nombreEmpleado el nombre del empleado que tiene esos customers
     * @return el nombre de los customers y el monto total
     * @throws Ejercicio6Exception
     */
    public static String obtenerVentas(String nombreEmpleado) throws Ejercicio6Exception {
        Properties properties = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(
                Path.of("Boletin_Tema7/src/main/java/Boletin1/Ejercicio6/ejercicio6.properties").toFile()))) {
            properties.load(br);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                /* En las consultas el WHERE siempre va antes que GROUP BY, agrupamos por el nombre,
                 * porque seleccionamos el nombre de customer, lo ideal es agrupar por lo que selecciono
                 * principalmente */
                PreparedStatement ps = connection.prepareStatement("select customers.customerName, SUM(payments.amount)" +
                        " from customers inner join employees ON customers.salesRepEmployeeNumber = employees.employeeNumber" +
                        " inner join payments ON customers.customerNumber = payments.customerNumber" +
                        " where employees.firstName LIKE (?) group by customers.customerName");
                ps.setString(1, nombreEmpleado);
                ResultSet rs = ps.executeQuery();
                StringBuilder sb = new StringBuilder();
                while (rs.next()) {
                    // Guardamos los nombres y el total en el StringBuilder
                    sb.append("Customer: ".concat(rs.getString(1)).concat(", ")
                            .concat("Monto total: ".concat(String.valueOf(rs.getDouble(2)))).concat("\n"));
                }
                return sb.toString();
            }

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio6Exception(e.getMessage());
        }
    }
}