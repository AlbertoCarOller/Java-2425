package Extra.ExamenCollections2024;

import java.util.Arrays;
import java.util.List;

public class TiendaApp {
    public static void main(String[] args) {
        try {
            // Crear una nueva tienda
            Tienda tienda = new Tienda("Tienda Online");

            // Crear categorías
            tienda.addCategoria("Electrónica");
            tienda.addCategoria("Hogar");
            tienda.addCategoria("Ropa");

            // Crear productos
            Producto producto1 = new Producto("Sony", "Xperia 5", "Smartphone", 799.99, 10);
            Producto producto2 = new Producto("Samsung", "Galaxy S21", "Smartphone", 999.99, 5);
            Producto producto3 = new Producto("Sony", "WH-1000XM4", "Auriculares", 349.99, 0);
            Producto producto4 = new Producto("Nike", "Air Max 90", "Zapatillas deportivas", 120.00, 15);

            // Crear listas de categorías para añadir productos
            List<Categoria> categoriasParaProducto1 = Arrays.asList(new Categoria("Electrónica"), new Categoria("Hogar"));
            List<Categoria> categoriasParaProducto2 = Arrays.asList(new Categoria("Electrónica"));
            List<Categoria> categoriasParaProducto3 = Arrays.asList(new Categoria("Electrónica"));
            List<Categoria> categoriasParaProducto4 = Arrays.asList(new Categoria("Ropa"));

            // Añadir productos a las categorías
            tienda.addProducto(producto1, categoriasParaProducto1);
            tienda.addProducto(producto2, categoriasParaProducto2);
            tienda.addProducto(producto3, categoriasParaProducto3);
            tienda.addProducto(producto4, categoriasParaProducto4);

            // Mostrar productos ordenados por precio
            System.out.println("Productos ordenados por precio (de mayor a menor):");
            tienda.getTodosLosProductosOrdenadosPorPrecio().forEach(System.out::println);

            // Ver las categorías con productos sin stock
            System.out.println("\nCategorías con productos sin stock:");
            tienda.categoriasConProductosSinStock().forEach(System.out::println);

            // Ver las categorías a las que pertenece un producto
            System.out.println("\nCategorías del producto 'Sony WH-1000XM4':");
            tienda.categoriasDeProducto(producto3).forEach(System.out::println);

            // Eliminar un producto
            System.out.println("\nEliminando el producto 'Sony Xperia 5'");
            boolean eliminado = tienda.eliminaProducto(producto1);
            System.out.println("¿Producto eliminado? " + eliminado);

            // Ver productos añadidos en el último año
            System.out.println("\nProductos añadidos en el último año:");
            tienda.productosUltimoAnno().forEach(System.out::println);

        } catch (TiendaException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}