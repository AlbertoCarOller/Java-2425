import java.util.Objects;

public class Libro {

    // Atributos
    private String nombre;
    private String autor;
    private String sinopsis;
    private int numeroEjemplares;
    private int ejemplaresDisponibles;

    // Hacemos un constructor
    public Libro(String nombre, String sinopsis, String autor) {
        this.nombre = nombre;
        this.numeroEjemplares = 5;
        this.ejemplaresDisponibles = 2;
        this.sinopsis = sinopsis;
        this.autor = autor;
    }

    // Hacemos los get
    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public int getNumeroEjemplares() {
        return numeroEjemplares;
    }

    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    // Hacemos los set
    public void setNumeroEjemplares(int numeroEjemplares) {
        this.numeroEjemplares = numeroEjemplares;
    }

    public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    // Hacemos un equals para comparar si el nombre y autor de un libro es el mismo, si es así, serán el mismo libro
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(nombre, libro.nombre) && Objects.equals(autor, libro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, autor);
    }

    // Hacemos un toString para imprimir los datos de los libros
    @Override
    public String toString() {
        return "Libro{" +
                "nombre='" + nombre + '\'' +
                ", autor='" + autor + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", numeroEjemplares=" + numeroEjemplares +
                ", ejemplaresDisponibles=" + ejemplaresDisponibles +
                '}';
    }
}