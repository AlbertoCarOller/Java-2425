package Extra.Ejercicio2E;

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

public class Ejercicio2E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/java/Extra/Ejercicio2E/ejercicio2E.properties");
            try (Connection connection = establecerConexion(properties)) {
                //inserccionMasivaTest(connection);
                eliminarTest(connection);
            }
        } catch (Ejercicio2EException | SQLException e) {
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
     * @throws Ejercicio2EException
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio2EException {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio2EException(e.getMessage());
        }
    }

    /**
     * Este método crea una transacción en la cual se intentan insertar 500 productos
     * pero mediante un Batch (lote), esto hará que sea más eficiente, ya que se accederá
     * una sola conexión a la base de datos, no una por cada sentencia
     * @param connection la conexión a la base de datos
     * @throws Ejercicio2EException
     */
    public static void inserccionMasivaTest(Connection connection) throws Ejercicio2EException {
        try {
            // Comienza la transacción
            connection.setAutoCommit(false);
            // Creamos la sentencia para insertar
            PreparedStatement ps = connection.prepareStatement("insert into products (productCode, productName, " +
                    "productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // Creamos la parte que nunca va a cambiar del código
            String parteCodigoS = "Test_";
            // Preparamos 500 inserciones y lo metemos dentro del lote (batch)
            for (int i = 0; i < 500; i++) {
                // Creamos los campos
                ps.setString(1, parteCodigoS.concat(String.valueOf(i)));
                ps.setString(2, "tester_".concat(String.valueOf(i)));
                ps.setString(3, "trains");
                ps.setString(4, "1:10");
                ps.setString(5, "tester_factory_".concat(String.valueOf(i)));
                ps.setString(6, "descripción de tester_".concat(String.valueOf(i)));
                ps.setInt(7, (int) (Math.random() * 50) + 1);
                ps.setDouble(8, (Math.random() * 100) + 10);
                ps.setDouble(9, (Math.random() * 100) + 110);
                // Se añade al lote
                ps.addBatch();
            }
            /* .executeBatch ejecuta en este caso las 500 sentencias añadidas a lote y devuelve un array de enteros
             * en el cada elemento representa las líneas afectadas por cada sentencia */
            ps.executeBatch();
            // Se hace commit para guardar los cambios
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            try {
                // En caso de que algo salga mal no se guardan los cambios
                connection.rollback();
                // Se activa el auto-commit
                connection.setAutoCommit(true);

            } catch (SQLException e1) {
                throw new Ejercicio2EException(e.getMessage());
            }
        }
    }

    /**
     * Este método va a eliminar de la tabla 'products' los productos cuyo
     * nombre comience por 'Test_'
     * @param connection la conexión a la base de datos
     * @throws Ejercicio2EException
     */
    public static void eliminarTest(Connection connection) throws Ejercicio2EException {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from products where productCode" +
                    " like 'Test_%'");
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new Ejercicio2EException(e.getMessage());
        }
    }
}