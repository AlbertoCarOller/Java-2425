package Extra.Ejercicio13;

import java.util.Objects;

public class Libro {

    // Creamos los atributos
    private String titulo;
    private String genero;
    private int numPaginas;

    // Creamos el constructor
    public Libro(String titulo, String genero, int numPaginas) throws LibroException {
        this.titulo = titulo;
        this.genero = genero.trim();
        setNumPaginas(numPaginas);
    }

    // Hacemos los get y set
    public String getTitulo() {
        return titulo;
    }

    private void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    private void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    private void setNumPaginas(int numPaginas) throws LibroException {
        if (numPaginas < 10) {
            throw new LibroException("Un libro no puede tener menos de 10 páginas");
        }
        this.numPaginas = numPaginas;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(titulo, libro.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Título: %s, Género: %s, Número de páginas: %d", this.titulo, this.genero, this.numPaginas);
    }
}