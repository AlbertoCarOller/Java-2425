package Extra.ExamenCollections2024;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Tienda {
    private String nombre;
    private Set<Categoria> categorias;

    public Tienda(String nombre) {
        this.nombre = nombre;
        this.categorias = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Añade una nueva categoría. En caso de que exista, lanza una excepción
     *
     * @param nombre
     * @throws TiendaException
     */
    public void addCategoria(String nombre) throws TiendaException {
        if (!categorias.add(new Categoria(nombre))) {
            throw new TiendaException("La categoría ya existe");
        }
    }

    /**
     * Añade un producto a una lista de categorías. Se añadirá a aquellas categorías en las que todavía no exista. En
     * aquellas donde ya exista, no se hará nada.
     *
     * @param p
     * @param categorias
     */
    public void addProducto(Producto p, List<Categoria> categorias) throws TiendaException {
        /*this.categorias.stream().filter(c -> this.categorias.stream()
                        .filter(c1 -> categorias.stream().anyMatch(c1::equals)).count() == categorias.size())
                .filter(c -> {
                    if (c.getProductos().add(p)) {
                        return true;
                    }
                    return true;
                }).findAny().orElseThrow(() -> new TiendaException("No coinciden las categorías"));*/
        if (this.categorias.stream().filter(c -> categorias.stream().anyMatch(c::equals)).count() != categorias.size()) {
            throw new TiendaException("Hay categorías no coincidentes");
        }
        this.categorias.stream().filter(c -> categorias.stream().anyMatch(c::equals))
                .forEach(c -> c.getProductos().add(p));
    }

    /**
     * Devuelve un conjunto con aquellas categorías que contienen algún producto sin stock
     *
     * @return
     */
    public Set<Categoria> categoriasConProductosSinStock() {
        return categorias.stream().filter(c -> c.getProductos().stream().anyMatch(p -> p.getStock() == 0))
                .collect(Collectors.toSet());
    }

    /**
     * Devuelve un conjunto con las categorías a las que pertenece un producto determinado
     *
     * @param p
     * @return
     */
    public Set<Categoria> categoriasDeProducto(Producto p) {
        return categorias.stream().filter(c -> c.getProductos().contains(p))
                .collect(Collectors.toSet());
    }

    /**
     * Devuelve un listado con todos los productos de la tienda (sin repetir) ordenados por precio de mayor a menor
     *
     * @return
     */
    public List<Producto> getTodosLosProductosOrdenadosPorPrecio() {
        return categorias.stream().flatMap(c -> c.getProductos().stream()).distinct()
                .sorted((c1, c2) -> Double.compare(c2.getPrecio(), c1.getPrecio())).toList();
    }

    /**
     * Elimina un producto de todas las categorías donde aparezca.
     *
     * @param p
     * @return true si el producto aparecía en alguna categoría
     */
    public boolean eliminaProducto(Producto p) {
        return categorias.stream().filter(c -> c.getProductos().remove(p))
                .count() >= 1;
    }

    /**
     * Devuelve un conjunto con aquellos productos que han sido añadido en el último año (a partir de la fecha actual)
     *
     * @return
     */
    public Set<Producto> productosUltimoAnno() {
        return categorias.stream().flatMap(c -> c.getProductos().stream()).distinct()
                .filter(p -> !p.getFechaIncorporacion().isBefore(LocalDate.now().minusYears(1)))
                .collect(Collectors.toSet());
    }
}