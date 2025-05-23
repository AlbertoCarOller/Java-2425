package Extra.Ejercicio4E;

import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio4E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/java/Extra/Ejercicio4E/ejercicio4E.properties");
            try (Connection connection = establecerConexion(properties)) {
                menuPrincipal(connection);
            }
        } catch (SQLException | Ejercicio4EException e) {
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
     * @throws Ejercicio4EException
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio4EException {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio4EException(e.getMessage());
        }
    }
    //-Primera parte-

    /**
     * Este método va a comprobar si el pedido existe y si
     * su campo 'shippedDate' es null
     *
     * @param codigoPedido el código del pedido a comprobar
     * @param connection   la conexión con la base de datos
     * @return devuelve si es válido o no
     * @throws Ejercicio4EException
     */
    public static boolean validacionPedido(int codigoPedido, Connection connection) throws Ejercicio4EException {
        try {
            PreparedStatement ps = connection.prepareStatement("select shippedDate from orders where" +
                    " orderNumber = ?");
            ps.setInt(1, codigoPedido);
            ResultSet rs = ps.executeQuery();
            // Si hay filas entra
            if (rs.next()) {
                rs.getDate(1);
                // Se comprueba si el campo 'shippedDate' es null, si es así, entra
                if (rs.wasNull()) {
                    return true;
                }
            }
            return false;

        } catch (SQLException e) {
            throw new Ejercicio4EException(e.getMessage());
        }
    }

    /**
     * Este método va a actualizar el campo del envío 'shippedDate'
     * a la fecha actual
     *
     * @param codigoPedido código del pedido
     * @param connection   la conexión a la base de datos
     * @throws Ejercicio4EException
     */
    public static void actualizarFechaEnvioPedido(int codigoPedido, Connection connection) throws Ejercicio4EException {
        try {
            PreparedStatement ps = connection.prepareStatement("update orders set shippedDate = current_date where" +
                    " orderNumber = ?");
            ps.setInt(1, codigoPedido);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Ejercicio4EException(e.getMessage());
        }
    }

    /**
     * Este método va a acumular en el lote todas las sentencias
     * para posteriormente se ejecuten todas las sentencias del
     * lote
     *
     * @param codigoPedido el código del pedido
     * @param ps           el PrepareStatement que va a contener todas las sentencias
     * @throws Ejercicio4EException
     */
    public static void actualizarFechaEnvioPedidoLotes(int codigoPedido, PreparedStatement ps) throws Ejercicio4EException {
        try {
            ps.setInt(1, codigoPedido);
            ps.addBatch();
        } catch (SQLException e) {
            throw new Ejercicio4EException(e.getMessage());
        }
    }

    //-Segunda parte-

    /**
     * Este método va a mostrar los pedidos sin enviar,
     * es decir que los que tengan el campo 'shippedDate'
     * como NULL, va a mostrar el código del pedido
     *
     * @param connection la conexión a la base de datos
     * @throws Ejercicio4EException
     */
    public static void mostrarPedidosSinEnviar(Connection connection) throws Ejercicio4EException {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select orderNumber from orders where shippedDate is null");
            while (rs.next()) {
                System.out.println("Código producto: ".concat(String.valueOf(rs.getInt(1))));
            }

        } catch (SQLException e) {
            throw new Ejercicio4EException(e.getMessage());
        }
    }

    /**
     * Este método va a solicitar al usuario una serie de
     * número de pedidos, se validarán, uno a uno, en caso
     * de que alguno no sea válido, se lanzará excepción,
     * se irán guardando en una lista
     *
     * @param connection la conexión con la base de datos
     * @return una lista de los números del pedido
     * @throws Ejercicio4EException
     */
    public static void solicitarPedidos(Connection connection) throws Ejercicio4EException {
        try {
            // Acumulamos los número de los pedidos
            int numPedido;
            boolean pedirMas = false;
            connection.setAutoCommit(false);
            try {
                PreparedStatement ps = connection.prepareStatement("update orders set shippedDate = current_date where" +
                        " orderNumber = ?");
                mostrarPedidosSinEnviar(connection);
                do {
                    numPedido = MiEntradaSalida.solicitarEntero("Introduce el número del pedido");
                    if (validacionPedido(numPedido, connection)) {
                        actualizarFechaEnvioPedidoLotes(numPedido, ps);
                    }
                    int op = MiEntradaSalida.seleccionarOpcion("Elija una opción", new String[]{"Introducir más" +
                            " pedidos", "Terminar"});
                    switch (op) {
                        case 1 -> pedirMas = true;
                        case 2 -> pedirMas = false;
                    }
                } while (pedirMas);
                // Ejecutamos todas las sentencias del lote
                ps.executeBatch();
                // Se guardan los cambios
                connection.commit();
                connection.setAutoCommit(true);

            } catch (Ejercicio4EException e) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new Ejercicio4EException(e.getMessage());
        }
    }

    // -Tercera parte-

    /**
     * Este método va a obtener a partir del nombre de un cliente,
     * el número de pedidos que ha realizado y el importe total
     *
     * @param nombreCliente el nombre del cliente
     * @param connection    la conexión con la base de datos
     * @throws Ejercicio4EException
     */
    public static void resumenPedido(String nombreCliente, Connection connection) throws Ejercicio4EException {
        try {
            // Obtenemos el número del cliente a partir de su nombre
            PreparedStatement ps = connection.prepareStatement("select customerNumber from customers" +
                    " where customerName like ?");
            ps.setString(1, nombreCliente);
            ResultSet rs = ps.executeQuery();
            // El total de pedidos realizados
            int totalPedidos = 0;
            double importeTotalPedidos = 0;
            if (rs.next()) {
                // Almacenamos el número del cliente
                int numCliente = rs.getInt(1);
                PreparedStatement ps1 = connection.prepareStatement("select count(orderNumber) from" +
                        " orders where customerNumber = ?");
                ps1.setInt(1, numCliente);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    totalPedidos = rs1.getInt(1);
                }
                // Obtenemos los números de los pedidos para poder consultar los datos 'del orderDetails'
                PreparedStatement ps3 = connection.prepareStatement("select orderNumber from orders" +
                        " where customerNumber = ?");
                ps3.setInt(1, numCliente);
                ResultSet rs2 = ps3.executeQuery();
                while (rs2.next()) {
                    int orderNumber = rs2.getInt(1);
                    // Obtenemos el importe total del pedido del cliente
                    PreparedStatement ps4 = connection.prepareStatement("select (quantityOrdered * priceEach)" +
                            " from orderDetails where orderNumber = ?");
                    ps4.setInt(1, orderNumber);
                    ResultSet rs3 = ps4.executeQuery();
                    if (rs3.next()) {
                        // Sumamos al importe total el importe del pedido repasado
                        importeTotalPedidos += rs3.getDouble(1);
                    }
                }
            }
            // Mostramos el resumen con los datos pedidos
            System.out.println("Nombre cliente: " + nombreCliente + ", Pedidos: " + totalPedidos +
                    ", Importe total: " + importeTotalPedidos + " euros");

        } catch (SQLException e) {
            throw new Ejercicio4EException(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar un menú con 3 opciones,
     * el usuario deberá de elegir y se llama a los
     * métodos correspondientes
     *
     * @param connection la conexión a la base de datos
     * @throws Ejercicio4EException
     */
    public static void menuPrincipal(Connection connection) throws Ejercicio4EException {
        int op = MiEntradaSalida.seleccionarOpcion("Elija una opción", new String[]{"Actualizar fecha pedido",
                "Actualizar fecha de varios pedidos", "Mostrar resumen pedidos"});
        switch (op) {
            case 1 -> {
                int codigo = MiEntradaSalida.solicitarEntero("Introduce el código del pedido");
                if (validacionPedido(codigo, connection)) {
                    actualizarFechaEnvioPedido(codigo, connection);
                }
            }
            case 2 -> solicitarPedidos(connection);
            case 3 -> {
                String nombreCliente = MiEntradaSalida.solicitarCadena("Introduce el nombre del cliente");
                resumenPedido(nombreCliente, connection);
            }
        }
    }
}