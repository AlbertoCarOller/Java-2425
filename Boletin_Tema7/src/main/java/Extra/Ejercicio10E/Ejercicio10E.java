package Extra.Ejercicio10E;

import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio10E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/resources/classicmodels.properties");
            try (Connection connection = establecerConexion(properties)) {
                String fechaIni = MiEntradaSalida.solicitarCadena("Introduce la fecha de inicio");
                String fechaFin = MiEntradaSalida.solicitarCadena("Introduce la fecha de fin");
                mostrarResumenPedido(fechaIni, fechaFin, connection);
            }

        } catch (InvalidPathException | SQLException | Ejercicio10EException e) {
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
     * @throws Ejercicio10EException
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio10EException {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio10EException(e.getMessage());
        }
    }

    /**
     * Este método va a comprobar que las fechas pasadas por
     * parámetros sean válidas y que vayan consecutivamente,
     * después se mostrará un resumen de cada pedido
     *
     * @param fechaInicio la fecha de rango inicial
     * @param fechaFin    la fecha de rango final
     * @param connection  la conexión con la base de datos
     * @throws Ejercicio10EException
     */
    public static void mostrarResumenPedido(String fechaInicio, String fechaFin, Connection connection) throws Ejercicio10EException {
        try {
            if (fechaInicio.matches("\\d{4}(-\\d{2}){2}") && fechaFin.matches("\\d{4}(-\\d{2}){2}")) {
                if (Date.valueOf(fechaInicio).before(Date.valueOf(fechaFin))) {
                    PreparedStatement ps = connection.prepareStatement("select orders.orderDate, customers.customerName," +
                            " orders.orderNumber, orderdetails.quantityOrdered * orderdetails.priceEach from orders inner join" +
                            " orderdetails on orders.orderNumber = orderdetails.orderNumber" +
                            " inner join customers on orders.customerNumber = customers.customerNumber" +
                            " where orderDate between? and ?");
                    ps.setDate(1, Date.valueOf(fechaInicio));
                    ps.setDate(2, Date.valueOf(fechaFin));
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getDate(1) + ", " + rs.getString(2) + ", " +
                                rs.getInt(3) + ", " + rs.getDouble(4));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}