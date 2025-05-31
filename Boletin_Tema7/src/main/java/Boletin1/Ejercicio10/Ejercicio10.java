package Boletin1.Ejercicio10;
import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio10 {
    public static void main(String[] args) {
        try (Connection connection = establecerConexion(Path.of(
                "Boletin_Tema7/src/main/resources/classicmodels.properties"))) {
            menuPrincipal(connection);

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
     * Este método va a mostrar todas las categorías de
     * todos los productos
     *
     * @param connection la conexión a la base de datos
     * @throws Ejercicio10Exception
     */
    public static void mostrarCategorias(Connection connection) throws Ejercicio10Exception {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select DISTINCT productLine from products");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a validar que la categoría
     * introducida por parámetros existe
     *
     * @param categoria  el nombre de la categoría a validar
     * @param connection la conexión con la base de datos
     * @return si es correcta o no
     * @throws Ejercicio10Exception
     */
    public static boolean validarCategoria(String categoria, Connection connection) throws Ejercicio10Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("select productCode from products " +
                    "where productLine like ?");
            ps.setString(1, categoria);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar el nombre y precio de los productos
     * que pertenezcan a la categoría en concreto
     *
     * @param categoria  la categoría de los productos
     * @param connection la conexión a la base de datos
     * @throws Ejercicio10Exception
     */
    public static void mostrarProductosCategoria(String categoria, Connection connection) throws Ejercicio10Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("select DISTINCT productName, MSRP from products" +
                    " where productLine like ?");
            ps.setString(1, categoria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Producto: ".concat(rs.getString(1).concat(" ,Precio: ")
                        .concat(String.valueOf(rs.getDouble(2)))));
            }

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a comprobar que el código del producto
     * exista
     *
     * @param codigoProducto el código del producto a validar
     * @param connection     la conexión a la base de datos
     * @return si es válido o no
     * @throws Ejercicio10Exception
     */
    public static boolean validarCodigoProducto(String codigoProducto, Connection connection) throws Ejercicio10Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("select productName from products where" +
                    " productCode like ?");
            ps.setString(1, codigoProducto);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a validar si hay stock suficiente para el producto
     * específico
     *
     * @param codigo     el código del producto
     * @param unidades   las unidades solicitadas
     * @param connection la conexión a la base de datos
     * @return si es correcto el número de unidades o no
     * @throws Ejercicio10Exception
     */
    public static boolean validarUnidades(String codigo, int unidades, Connection connection) throws Ejercicio10Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from products where" +
                    " productCode like ? AND quantityInStock >= ?");
            ps.setString(1, codigo);
            ps.setInt(2, unidades);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a restar el stock de productos disponibles
     * del producto del cual se ha hecho el pedido
     *
     * @param codigo     el código del producto
     * @param unidades   las unidades a restar
     * @param connection la conexión a la base de datos
     * @throws Ejercicio10Exception
     */
    public static void restarStock(String codigo, int unidades, Connection connection) throws Ejercicio10Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("update products set quantityInStock =" +
                    " quantityInStock - ? where productCode like ?");
            ps.setInt(1, unidades);
            ps.setString(2, codigo);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a crear el pedido que se va a ver
     * reflejado en la base de datos
     *
     * @param numCliente el número del cliente
     * @param connection la conexión a la base de datos
     * @throws Ejercicio10Exception
     */
    public static int crearPedido(int numCliente, Connection connection) throws Ejercicio10Exception {
        try {
            // Obtenemos el 'orderNumber' mayor y le sumamos 1, ya que van consecutivo
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select MAX(orderNumber) from orders");
            int orderNumberNuevo;
            if (rs.next()) {
                orderNumberNuevo = rs.getInt(1) + 1;

            } else {
                throw new Ejercicio10Exception("No se ha podido obtener el 'orderNumber' mayor");
            }
            // Creamos la order
            PreparedStatement ps = connection.prepareStatement("insert into orders (orderNumber, orderDate," +
                    " requiredDate, status, customerNumber) VALUES (?, CURRENT_DATE, CURRENT_DATE + INTERVAL 10 DAY," +
                    " ?, ?)");
            ps.setInt(1, orderNumberNuevo);
            ps.setString(2, "in progress");
            ps.setInt(3, numCliente);
            ps.executeUpdate();
            return orderNumberNuevo;

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a crear los detalles del pedido
     * del producto concreto
     *
     * @param orderNumber    el número del pedido
     * @param codigoProducto el código del producto
     * @param cantidad       la cantidad pedida del producto
     * @param connection     la conexión a la base de datos
     * @throws Ejercicio10Exception
     */
    public static void crearDetallesPedido(int orderNumber, String codigoProducto, int cantidad, Connection connection)
            throws Ejercicio10Exception {
        try {
            // Obtenemos el precio del producto al que está en venta
            PreparedStatement ps = connection.prepareStatement("select MSRP from products where productCode like ?");
            ps.setString(1, codigoProducto);
            ResultSet rs = ps.executeQuery();
            double precioPorProducto;
            if (rs.next()) {
                precioPorProducto = rs.getDouble(1);

            } else {
                throw new Ejercicio10Exception("No de ha podido obtener el precio por producto");
            }
            PreparedStatement ps1 = connection.prepareStatement("insert into orderDetails (orderNumber, " +
                    "productCode, quantityOrdered, priceEach, orderLineNumber) VALUES (?, ?, ?, ?, ?)");
            ps1.setInt(1, orderNumber);
            ps1.setString(2, codigoProducto);
            ps1.setInt(3, cantidad);
            ps1.setDouble(4, precioPorProducto);
            ps1.setInt(5, (int) (Math.random() * 15) + 1);
            ps1.executeUpdate();

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a actualizar el número de productos del
     * pedido
     *
     * @param orderNumber   el número del pedido a actualizar
     * @param cantidadNueva la cantidad nueva del producto
     * @param connection    la conexión con la base de datos
     * @throws Ejercicio10Exception
     */
    public static void actualizarPedidoDetalles(int orderNumber, int cantidadNueva, Connection connection)
            throws Ejercicio10Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("update orderdetails set quantityOrdered = ?" +
                    " where orderNumber = ?");
            ps.setInt(1, cantidadNueva);
            ps.setInt(2, orderNumber);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método va a devolver el total de lo que ha costado el pedido
     * creado anteriormente
     *
     * @param orderNumber el número del pedido creado anteriormente
     * @param connection  la conexión a la base de datos
     * @return el total de este pedido
     * @throws Ejercicio10Exception
     */
    public static double acumularTotal(int orderNumber, Connection connection) throws Ejercicio10Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("select quantityOrdered * priceEach from" +
                    " orderdetails where orderNumber = ?");
            ps.setInt(1, orderNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);

            } else {
                throw new Ejercicio10Exception("No se puede calcular el total del pedido");
            }

        } catch (SQLException e) {
            throw new Ejercicio10Exception(e.getMessage());
        }
    }

    /**
     * Este método es un menú el cual va a preguntar al usuario
     * si quiere crear un producto o hacer un pedido
     *
     * @param connection la conexión a la base de datos
     */
    public static void menuPrincipal(Connection connection) {
        int op = MiEntradaSalida.seleccionarOpcion("Elija una opción:",
                new String[]{"Crear producto", "Hacer pedido"});
        switch (op) {
            case 1 -> menuCrearProducto(connection);
            case 2 -> menuPedido(connection);
        }
    }

    /**
     * Este es el menú de creación de productos
     *
     * @param connection la conexión a la base de datos
     */
    public static void menuCrearProducto(Connection connection) {
        try {
            // Solicitamos los datos
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

        } catch (Ejercicio10Exception e) {
            System.out.println("No se ha podido crear el producto, motivo: " + e.getMessage());
        }
    }

    /**
     * Este método va a mostrar un menú para la
     * creación de un pedido, con sus respectas
     * validaciones
     *
     * @param connection la conexión a la base de datos
     */
    public static void menuPedido(Connection connection) {
        try {
            // Empezamos la transacción
            connection.setAutoCommit(false);
            // Creamos la información del resumen del pedido
            StringBuilder sb = new StringBuilder();
            // Solicitamos y comprobamos la existencia del número del cliente
            int numCliente = -1;
            // Mientras el número del cliente no sea correcto se seguirá solicitando
            while (!validarNumCliente(numCliente, connection)) {
                numCliente = MiEntradaSalida.solicitarEntero("Introduce el número del cliente");
            }
            // Guardamos el código del cliente en el resumen
            sb.append("Cliente: ").append(numCliente);
            int op = 1;
            // Creamos la acumulación de lo que sale cada pedido
            double acumulacionPedido = 0;
            // Creamos un mapa donde se va a guardar código del producto y el código del pedido
            Map<String, Integer> codProductoPedido = new HashMap<>();
            // La cantidad de productos acumulados del cliente
            int cantidadAcumuladaProducto = 0;
            // Mientras 'op' sea 1 continúa, la primera vez va a ser 1, después depende del usuario
            while (op == 1) {
                // Se solicita la categoría y se valida
                String categoria = "";
                // Mientras no sea correcta se seguirá solicitando
                while (!validarCategoria(categoria, connection)) {
                    // Mostramos las categorías de los productos
                    mostrarCategorias(connection);
                    categoria = MiEntradaSalida.solicitarCadena("Introduce la categoría del producto");
                }
                // Se añade la categoría al resumen
                sb.append(", Categoría producto: ").append(categoria);
                // Solicitamos el código del producto y comprobamos que sea válido
                String codigoProducto = "";
                while (!validarCodigoProducto(codigoProducto, connection)) {
                    // Mostramos los productos de la categoría
                    mostrarProductosCategoria(categoria, connection);
                    codigoProducto = MiEntradaSalida.solicitarCadena("Introduce el código del producto");
                }
                int cantidadPedida;
                if (sb.toString().contains(codigoProducto)) {
                    // Obtenemos la cantidad de ese producto pedido hasta el momento mediante expresiones regulares
                    Pattern pattern = Pattern.compile(", (?<Contenido>Código\\sproducto:\\s" + codigoProducto + "," +
                            "\\sCantidad:\\s)(?<Cantidad>\\d+), ", Pattern.UNICODE_CHARACTER_CLASS);
                    Matcher matcher = pattern.matcher(sb);
                    if (matcher.find()) {
                        cantidadAcumuladaProducto = Integer.parseInt(matcher.group("Cantidad"));
                    }
                    int cantidadPedirAhora = MiEntradaSalida.solicitarEntero("Introduce la cantidad a pedir");
                    while (!validarUnidades(codigoProducto, cantidadAcumuladaProducto + cantidadPedirAhora, connection)) {
                        cantidadPedirAhora = MiEntradaSalida.solicitarEntero("Introduce la cantidad a pedir");
                    }
                    // Guardamos la cantidad actualizada del producto pedido
                    cantidadPedida = cantidadAcumuladaProducto + cantidadPedirAhora;
                    // Hacemos una copia que no se va a modificar de 'cantidadPedida' para poder utilizarlo en el flujo
                    int copiaCantidadPedida = cantidadPedida;
                    // Actualizamos la cantidad del producto pedido
                    sb = new StringBuilder(matcher.replaceAll(m -> ", ".concat(m.group("Contenido"))
                            .concat(String.valueOf(copiaCantidadPedida)).concat(", ")));
                    // Restamos la cantidad nueva pedida del producto
                    restarStock(codigoProducto, cantidadPedirAhora, connection);
                    // En caso de que no se haya pedido antes este producto entra
                } else {
                    do {
                        cantidadPedida = MiEntradaSalida.solicitarEntero("Introduce la cantidad a pedir");
                    } while (!validarUnidades(codigoProducto, cantidadPedida, connection));
                    sb.append(", Código producto: ").append(codigoProducto).append(", Cantidad: ")
                            .append(cantidadPedida);
                    // Restamos la cantidad de productos del stock
                    restarStock(codigoProducto, cantidadPedida, connection);
                }
                // Almacenamos el código del pedido a crear o a actualizar
                int orderNumber;
                // En caso de que no exista ese producto en el pedido se añade como pedido nuevo
                if (!codProductoPedido.containsKey(codigoProducto)) {
                    // Creamos el pedido y guardamos el número del pedido
                    orderNumber = crearPedido(numCliente, connection);
                    // Creamos los detalles del pedido
                    crearDetallesPedido(orderNumber, codigoProducto, cantidadPedida, connection);
                    // Añadimos al resumen el código del pedido
                    sb.append(", Código pedido: ").append(orderNumber);
                    // Se guarda el código del producto y del pedido
                    codProductoPedido.put(codigoProducto, orderNumber);
                    // En caso de que exista el pedido, se actualizará la cantidad de productos del pedido
                } else {
                    // Obtenemos el número del pedido, para así poder actualizar el número de productos pedidos
                    orderNumber = codProductoPedido.get(codigoProducto);
                    actualizarPedidoDetalles(orderNumber, cantidadPedida, connection);
                }
                // Se le suma el total del pedido creado
                acumulacionPedido += acumularTotal(orderNumber, connection);
                // Hacemos el commit, es decir este pedido ya se vería reflejado en la base de datos
                connection.commit();
                // Se le da la opción al usuario de pedir más productos
                op = MiEntradaSalida.solicitarEnteroPositivo("Pulse 1 para hacer otro pedido," +
                        " cualquier otro para terminar");
            }
            // Se le añade al resumen el total
            sb.append("\n").append("\n").append("Total: ").append(acumulacionPedido);
            // Imprimimos el resumen
            System.out.println(sb);
            // Cambiamos el auto-commit a true, una vez que ya se han realizado todos los pedidos de este cliente
            connection.setAutoCommit(true);
        } catch (Ejercicio10Exception | SQLException e) {
            try {
                // En caso de que haya algún problema se hará un rollback
                connection.rollback();
                // Se cambia el auto-commit a true
                connection.setAutoCommit(true);
                System.out.println(e.getMessage());

            } catch (SQLException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }
}