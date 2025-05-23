package Extra.Ejercicio6E;


import utils.MiEntradaSalida;

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

public class Ejercicio6E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/java/Extra/Ejercicio6E/ejercicio6.properties");
            try (Connection connection = establecerConexion(properties)) {
                try {
                    // Comenzamos la transacción
                    connection.setAutoCommit(false);
                    int numPedido = MiEntradaSalida.solicitarEntero("Introduce el número del pedido a cancelar");
                    eliminarOrderDetails(connection, numPedido);
                    cambiarEstadoOrder(connection, numPedido);
                    // Si no surge ningún error se guardan los cambios
                    connection.commit();
                    connection.setAutoCommit(true);
                } catch (Ejercicio6EException e) {
                    try {
                        // En caso de error se deshacen los cambios
                        connection.rollback();
                        connection.setAutoCommit(true);
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                    }
                }
            }
        } catch (InvalidPathException | SQLException | Ejercicio6EException e) {
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
     * @throws Ejercicio6EException
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio6EException {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio6EException(e.getMessage());
        }
    }

    /**
     * Este método va a eliminar de orderDetails la fila
     * que tenga el código del pedido pasado por parámetros
     *
     * @param connection  la conexión con la base de datos
     * @param orderNumber el número del pedido
     * @throws Ejercicio6EException
     */
    public static void eliminarOrderDetails(Connection connection, int orderNumber) throws Ejercicio6EException {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from orderdetails where " +
                    "orderNumber = ?");
            ps.setInt(1, orderNumber);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new Ejercicio6EException(e.getMessage());
        }
    }

    /**
     * Este método va a cambiar el estado de la orden
     * con el orderNumber pasado por parámetros
     *
     * @param connection  la conexión con la base de datos
     * @param orderNumber el número de la orden
     * @throws Ejercicio6EException
     */
    public static void cambiarEstadoOrder(Connection connection, int orderNumber) throws Ejercicio6EException {
        try {
            PreparedStatement ps = connection.prepareStatement("update orders set status = 'Returned' " +
                    "where orderNumber = ?");
            ps.setInt(1, orderNumber);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new Ejercicio6EException(e.getMessage());
        }
    }
}