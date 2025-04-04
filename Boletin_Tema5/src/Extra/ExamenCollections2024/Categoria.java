package Extra.ExamenCollections2024;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Categoria {
    private String nombre;
    private Set<Producto> productos;

    // Creamos el constructor

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.productos = new LinkedHashSet<>();
    }

    // Hacemos los get
    public String getNombre() {
        return nombre;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    // Hacemos el equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Productos: %s", this.nombre, this.productos);
    }
}