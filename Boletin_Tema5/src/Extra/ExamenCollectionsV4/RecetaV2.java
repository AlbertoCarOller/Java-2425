package Extra.ExamenCollectionsV4;

import java.util.*;

public class RecetaV2 {
    private String nombre;
    private String autor;
    private String categoria;
    private Set<String> ingredientes;
    private List<ValoracionV2> valoraciones;

    public RecetaV2(String nombre, String autor, String categoria) {
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.ingredientes = new LinkedHashSet<>(); // Decidir que implementacion usar
        this.valoraciones = new LinkedList<>(); // Decidir que implementacion usar
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public Set<String> getIngredientes() {
        return ingredientes;
    }

    public List<ValoracionV2> getValoraciones() {
        return valoraciones;
    }

    // equals y hashCode basados en nombre y autor
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecetaV2 recetaV2 = (RecetaV2) o;
        return Objects.equals(nombre, recetaV2.nombre) && Objects.equals(autor, recetaV2.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, autor);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Autor: %s, Categoría: %s, Ingredientes: %s, Valoraciones: %s", this.nombre,
                this.autor, this.categoria, this.ingredientes, this.valoraciones);
    }

    // Hacemos un método para añadir una valoración en caso de que no exista
    public void anadirValoracion(ValoracionV2 valoracionV2) throws RecetarioException {
        if (valoraciones.contains(valoracionV2)) {
            throw new RecetarioException("La valoración ya exista");
        }
        valoraciones.add(valoracionV2);
    }

    // Hacemos un método para añadir un ingrediente
    public void anadirIngrediente(String ingrediente) throws RecetarioException {
        if (ingredientes.contains(ingrediente)) {
            throw new RecetarioException("El ingrediente ya existe");
        }
        ingredientes.add(ingrediente);
    }
}