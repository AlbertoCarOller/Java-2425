package Extra.Ejercicio11E;

import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class Ejercicio11E {
    public static void main(String[] args) {
        try {
            Path properties = Path.of("Boletin_Tema7/src/main/resources/my_test_db.properties");
            try (Connection connection = establecerConexion(properties)) {
                int op;
                do {
                    op = MiEntradaSalida.seleccionarOpcion("Elija una opción",
                            new String[]{"Insertar cliente", "Salir"});
                    switch (op) {
                        case 1 -> {
                            String customerName = MiEntradaSalida.solicitarCadena("Introduce el nombre del cliente");
                            String contactLastName = MiEntradaSalida.solicitarCadena("Introduce el segundo" +
                                    " nombre del contacto");
                            String contactFirstName = MiEntradaSalida.solicitarCadena("Introduce el primer" +
                                    " nombre del contacto");
                            String phone = MiEntradaSalida.solicitarCadena("Introduce el número de telefono");
                            String addressLine1 = MiEntradaSalida.solicitarCadena("Introduce la dirección");
                            String city = MiEntradaSalida.solicitarCadena("Introduce la ciudad");
                            String country = MiEntradaSalida.solicitarCadena("Introduce el país");
                            devolverKeysClientes(connection, customerName, contactLastName, contactFirstName, phone
                                    , addressLine1, city, country);
                        }
                        case 2 -> System.out.println("Hasta pronto");
                    }

                } while (op != 2);
            }

        } catch (InvalidPathException | SQLException e) {
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
     * @throws Ejercicio11EException
     */
    public static Connection establecerConexion(Path properties) throws Ejercicio11EException {
        Properties pro = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(properties.toFile()))) {
            pro.load(br);
            return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"),
                    pro.getProperty("db.password"));

        } catch (InvalidPathException | IOException | SQLException e) {
            throw new Ejercicio11EException(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar la clave del cliente insertado
     *
     * @param connection       la conexión a la base de datos
     * @param customerName     el nombre del cliente
     * @param contactLastName  el último nombre del contacto
     * @param contactFirstName el primer nombre del contacto
     * @param phone            el número de teléfono
     * @param addressLine1     la primera línea de dirección
     * @param city             la ciudad del cliente
     * @param country          el país del cliente
     * @throws Ejercicio11EException
     */
    public static void devolverKeysClientes(Connection connection, String customerName, String contactLastName,
                                            String contactFirstName, String phone, String addressLine1, String city,
                                            String country) throws Ejercicio11EException {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into customers (customerName, " +
                    "contactLastName, contactFirstName, phone, addressLine1, city, country)" +
                    " values (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customerName);
            ps.setString(2, contactLastName);
            ps.setString(3, contactFirstName);
            ps.setString(4, phone);
            ps.setString(5, addressLine1);
            ps.setString(6, city);
            ps.setString(7, country);
            ps.executeUpdate();
            // Obtenemos las claves generadas
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                // Mostramos por consola la clave que se ha generado al insertar al cliente
                System.out.println(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new Ejercicio11EException(e.getMessage());
        }
    }
}