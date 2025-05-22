package Extra.Ejercicio5E;

import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio5E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/java/Extra/Ejercicio5E/ejercicio5E.properties");
            try (Connection connection = establecerConexion(properties)) {
                String ciudad = MiEntradaSalida.solicitarCadena("Introduce la ciudad donde se ubica la oficina");
                obtenerEmpleadosOficina(ciudad, connection);
            }

        } catch (InvalidPathException | Ejercicio5EException | SQLException e) {
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
     * @throws Ejercicio5EException
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio5EException {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio5EException(e.getMessage());
        }
    }

    /**
     * Este método va a obtener el número de empleados
     * de la oficina de la ciudad correspondiente
     *
     * @param ciudad     la ciudad donde se encuentra la oficina
     * @param connection la conexión a la base de datos
     * @throws Ejercicio5EException
     */
    public static void obtenerEmpleadosOficina(String ciudad, Connection connection) throws Ejercicio5EException {
        try {
            PreparedStatement ps = connection.prepareStatement("select count(employeeNumber) from employees" +
                    " where officeCode = (select officeCode from offices where city like ?)");
            ps.setString(1, ciudad);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Empleados oficina: " + rs.getInt(1));
            }
            // Llamamos al otro método
            obtenerClientesEmpleados(ciudad, connection);

        } catch (SQLException e) {
            throw new Ejercicio5EException(e.getMessage());
        }
    }

    /**
     * Este método va a obtener y mostrar el número de clientes
     * que tiene un en empleado y el importe total pagado por
     * todos los clientes de todos los empleados que pertenecen
     * a la oficina
     *
     * @param ciudad     la ciudad de la oficina
     * @param connection la conexión con la base de datos
     * @throws Ejercicio5EException
     */
    public static void obtenerClientesEmpleados(String ciudad, Connection connection) throws Ejercicio5EException {
        try {
            // Obtenemos los empleados de la oficina específica
            PreparedStatement ps = connection.prepareStatement("select employeeNumber from employees" +
                    " where officeCode = (select officeCode from offices where city like ?)");
            ps.setString(1, ciudad);
            ResultSet rs = ps.executeQuery();
            int totalClientes = 0;
            double totalPagadoClientes = 0;
            while (rs.next()) {
                int employeeNumber = rs.getInt(1);
                // Obtenemos el número de clientes asociados a un empleado
                PreparedStatement ps1 = connection.prepareStatement("select count(customerNumber) from" +
                        " customers where salesRepEmployeeNumber = ?");
                ps1.setInt(1, employeeNumber);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    // Vamos sumando los clientes de los empleados pertenecientes a la oficina específica
                    totalClientes += rs1.getInt(1);
                }
                /* Obtenemos el número de las órdenes que tengan el cliente especificado, el 'IN' es importante
                 * aquí, ya que gracias a él se obtienen todos los customerNumber que pertenecen al empleado
                 * especificado, al utilizar el '=' te da solo uno */
                PreparedStatement ps2 = connection.prepareStatement("select orderNumber from orders" +
                        " where customerNumber in (select customerNumber from customers where " +
                        "salesRepEmployeeNumber = ?)");
                ps2.setInt(1, employeeNumber);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    int ordeNumber = rs2.getInt(1);
                    // Obtenemos el precio de cada pedido
                    PreparedStatement ps3 = connection.prepareStatement("select (quantityOrdered * priceEach)" +
                            " from orderdetails where orderNumber = ?");
                    ps3.setInt(1, ordeNumber);
                    ResultSet rs3 = ps3.executeQuery();
                    if (rs3.next()) {
                        // Vamos sumando el costo de cada pedido
                        totalPagadoClientes += rs3.getDouble(1);
                    }
                }
            }
            System.out.println("Total de clientes: " + totalClientes);
            System.out.println("Importe total clientes: " + totalPagadoClientes + " euros");

        } catch (SQLException e) {
            throw new Ejercicio5EException(e.getMessage());
        }
    }
}