package Extra.Ejercicio4E;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Ejercicio4E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/java/Extra/Ejercicio4E/ejercicio4E.properties");
            try (Connection connection = establecerConexion(properties)) {
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
    public static void validacionPedido(int codigoPedido, Connection connection) throws Ejercicio4EException {
        try {
            PreparedStatement ps = connection.prepareStatement("select shippedDate from orders where" +
                    " orderNumber = ?");
            ps.setInt(1, codigoPedido);
            ResultSet rs = ps.executeQuery();
            // Esto nos indicará si es válido o no y se lanzará excepción si no lo es
            boolean valido = false;
            // Si hay filas entra
            if (rs.next()) {
                rs.getDate(1);
                // Se comprueba si el campo 'shippedDate' es null, si es así, entra
                if (rs.wasNull()) {
                    valido = true;
                    // Se actualiza la fecha del pedido
                    actualizarFechaEnvioPedido(codigoPedido, connection);
                }
            }
            // Si no es válido se lanzará excepción
            if (!valido) {
                throw new Ejercicio4EException("El pedido no es válido");
            }

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
                    " = ?");
            ps.setInt(1, codigoPedido);
            ps.executeUpdate();
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

    public static void solicitarPedidos(Connection connection) throws Ejercicio4EException {
        try {
            // Acumulamos los número de los pedidos
            List<String> pedidosNum = new ArrayList<>();
            // TODO: terminar ejercicio
        }
    }
}