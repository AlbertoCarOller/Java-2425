package Boletin1.Ejercicio9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio9 {
    public static void main(String[] args) {
        try (Connection connection = establecerConexion(
                Path.of("Boletin_Tema7/src/main/java/Boletin1/Ejercicio9/ejercicio9.properties"))) {
            borrarInformacionEmpleado("Atelier graphique", connection);

        } catch (Ejercicio9Exception | SQLException e) {
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
     * @throws Ejercicio9Exception
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio9Exception {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio9Exception(e.getMessage());
        }
    }

    /**
     * Este método va a obtener el customerNumber mediante el nombre, para a´si
     * borrar de la tabla orders, las órdenes que tegan este customerNumber y
     * también se eliminará al propio customer de la tabla customers
     *
     * @param nombreCliente el nombre del customer a eliminar
     * @throws Ejercicio9Exception
     */
    public static void borrarInformacionEmpleado(String nombreCliente, Connection connection) throws Ejercicio9Exception {
        try {
            // Obtenemos el customerNumber para poder eliminar de las tablas que necesitan de este
            PreparedStatement ps = connection.prepareStatement("select * from customers where" +
                    " customerName LIKE (?)");
            ps.setString(1, nombreCliente);
            ResultSet rs = ps.executeQuery();
            int customerNumber;
            if (rs.next()) {
                customerNumber = rs.getInt("customerNumber");

            } else {
                throw new Ejercicio9Exception("No se ha encontrado al cliente");
            }
            // Obtenemos el orderNumber para poder borrar del orderDetails
            PreparedStatement ps4 = connection.prepareStatement("select orderNumber from orders where customerNumber" +
                    " = (?)");
            ps4.setInt(1, customerNumber);
            ResultSet rs1 = ps4.executeQuery();
            // Mientras haya orders continúa
            while (rs1.next()) {
                // Guardamos el orderNumber
                   int orderNumber = rs1.getInt(1);
                // Eliminamos la fila de orderDetails que nos impide eliminar el order, porque orderDetails depende de Order
                PreparedStatement ps5 = connection.prepareStatement("delete from orderDetails where orderNumber" +
                        " = (?)");
                ps5.setInt(1, orderNumber);
                ps5.executeUpdate();
            }
            // Eliminamos de la tabla orders la fila que contenga el customerNumber especificado
            PreparedStatement ps1 = connection.prepareStatement("delete from orders where customerNumber" +
                    " = (?)");
            ps1.setInt(1, customerNumber);
            ps1.executeUpdate();
            // Elimino de pagos la fila que contenga el customerNumber especificado
            PreparedStatement ps3 = connection.prepareStatement("delete from payments where customerNumber" +
                    " = (?)");
            ps3.setInt(1, customerNumber);
            ps3.executeUpdate();
            // Elimino de la tabla customers toda la fila que tenga el customerNumber especificado
            PreparedStatement ps2 = connection.prepareStatement("delete from customers where customerNumber" +
                    " = (?)");
            ps2.setInt(1, customerNumber);
            ps2.executeUpdate();

        } catch (InvalidPathException | SQLException e) {
            throw new Ejercicio9Exception(e.getMessage());
        }
    }
}