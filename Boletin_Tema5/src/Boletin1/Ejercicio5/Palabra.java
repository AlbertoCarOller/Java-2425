package Boletin1.Ejercicio5;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Palabra implements Comparable<Palabra> {

    // Creamos los atributos
    private String nombre;
    private Set<String> significados;

    // Creamos el constructor
    public Palabra(String nombre, String significado) throws DireccionExcepcion {
        setNombre(nombre);
        this.significados = new LinkedHashSet<>();
        // Le metemos el significado al crear una palabra
        this.significados.add(significado);
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws DireccionExcepcion {
        if (nombre.contains(" ")) {
            throw new DireccionExcepcion("El nombre no puede contener espacios");
        }
        this.nombre = nombre;
    }

    public Set<String> getSignificados() {
        return significados;
    }

    private void setSignificados(Set<String> significado) {
        this.significados = significado;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palabra palabra = (Palabra) o;
        return Objects.equals(nombre, palabra.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre de la palabra: %s\nSignificados de la palabra: %s\n", this.nombre, this.significados);
    }

    @Override
    public int compareTo(Palabra o) {
        return this.nombre.compareTo(o.nombre);
    }
}