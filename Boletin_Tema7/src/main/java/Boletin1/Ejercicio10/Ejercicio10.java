package Boletin1.Ejercicio10;

import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio10 {
    public static void main(String[] args) {
        try (Connection connection = establecerConexion(Path.of(
                "Boletin_Tema7/src/main/java/Boletin1/Ejercicio10/ejercicio10.properties"))) {
            mostrarMenuPreguntas(connection);

        } catch (Ejercicio10Exception | SQLException e) {
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
     * @throws Ejercicio10Exception
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio10Exception {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a insertar el producto formado por los parámetros pasados
     * en la tabla 'products'
     *
     * @param codigoProducto        el código del producto
     * @param nombreProducto        el nombre del producto
     * @param categoriaProducto     la categoría del producto
     * @param escalaProducto        la escala del producto
     * @param proveedorProducto     el proveedor del producto
     * @param descripcionProducto   la descripción del producto
     * @param cantidadStockProducto la cantidad en stock del producto
     * @param precioCompraProducto  el precio de compra del producto
     * @param msrpProducto          el msrp del producto
     * @param connection            la conexión a la base de datos
     * @throws Ejercicio10Exception
     */
    public static void insertarNuevoProducto(String codigoProducto, String nombreProducto, String categoriaProducto,
                                             String escalaProducto, String proveedorProducto, String descripcionProducto,
                                             int cantidadStockProducto, double precioCompraProducto, double msrpProducto,
                                             Connection connection) throws Ejercicio10Exception {
        try {
            // Insertamos el nuevo producto
            PreparedStatement ps = connection.prepareStatement("insert into products (productCode, productName, " +
                    "productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, " +
                    "MSRP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, codigoProducto);
            ps.setString(2, nombreProducto);
            ps.setString(3, categoriaProducto);
            ps.setString(4, escalaProducto);
            ps.setString(5, proveedorProducto);
            ps.setString(6, descripcionProducto);
            ps.setInt(7, cantidadStockProducto);
            ps.setDouble(8, precioCompraProducto);
            ps.setDouble(9, msrpProducto);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a validar que exista el cliente con
     * el número pasado por parámetros, se mostrará un
     * mensaje por consola en caso de que se haya encontrado
     * el cual mostrará su nombre
     *
     * @param numeroCliente el número del cliente a validar
     * @param connection    la conexión a la base de datos
     * @return si el número es válido o no
     * @throws Ejercicio10Exception
     */
    public static boolean validarNumCliente(int numeroCliente, Connection connection) throws Ejercicio10Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("select customerName from" +
                    " customers where customerNumber = ?");
            ps.setInt(1, numeroCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Se ha seleccionado al cliente " + rs.getString(1));
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a actuar como menú y va a solicitar
     * los datos correspondiente al usuario
     *
     * @throws Ejercicio10Exception
     */
    public static void mostrarMenuPreguntas(Connection connection) throws Ejercicio10Exception {
        // Solicitamos los datos al usuario
        String codigoProducto = MiEntradaSalida.solicitarCadena("Introduce el código del producto");
        String nombreProducto = MiEntradaSalida.solicitarCadena("Introduce el nombre del producto");
        String categoriaProducto = MiEntradaSalida.solicitarCadena("Introduce la categoría del producto");
        String escalaProducto = MiEntradaSalida.solicitarCadena("Introduce la escala del producto");
        String proveedorProducto = MiEntradaSalida.solicitarCadena("Introduce el proveedor del producto");
        String descripcionProducto = MiEntradaSalida.solicitarCadena("Introduce la descripción del producto");
        int cantidadStockProducto = MiEntradaSalida.solicitarEntero("Introduce la cantidad en stock del producto");
        double precioCompraProducto = MiEntradaSalida.solicitarDoublePositivo("Introduce el precio de compra" +
                " del producto");
        double msrpProducto = MiEntradaSalida.solicitarDoublePositivo("Introduce el msrp del producto");
        // Insertamos el nuevo producto a la base de datos
        insertarNuevoProducto(codigoProducto, nombreProducto, categoriaProducto, escalaProducto, proveedorProducto,
                descripcionProducto, cantidadStockProducto, precioCompraProducto, msrpProducto, connection);
        // Solicitamos y comprobamos la existencia del número del cliente
        int numCliente = -1;
        // Mientras el número del cliente no sea correcto se seguirá solicitando
        while (!validarNumCliente(numCliente, connection)) {
            numCliente = MiEntradaSalida.solicitarEntero("Introduce el número del cliente");
        }
    }
}