package Extra.Ejercicio13;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Autor {

    // Creamos los atributos
    private String nombre;
    private List<Libro> librosEnviados;

    // Creamos el constructor
    public Autor(String nombre) throws LibroException {
        setNombre(nombre);
        this.librosEnviados = new ArrayList<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws LibroException {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new LibroException("El nombre del autor debe empezar por mayúsculas");
        }
        for (int i = 1; i < nombre.length(); i++) {
            if (Character.isUpperCase(nombre.charAt(i)) || !Character.isLetter(nombre.charAt(i))) {
                throw new LibroException("El resto de palabras debe estar en minúsculas y ser letras");
            }
        }
        this.nombre = nombre;
    }

    public List<Libro> getLibrosEnviados() {
        return librosEnviados;
    }

    private void setLibrosEnviados(List<Libro> librosEnviados) {
        this.librosEnviados = librosEnviados;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nombre, autor.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Libros enviados: %s", this.nombre, this.librosEnviados);
    }
}