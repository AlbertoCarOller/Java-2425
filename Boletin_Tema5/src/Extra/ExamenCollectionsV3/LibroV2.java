package Extra.ExamenCollectionsV3;

import java.util.Objects;

public class LibroV2 {

    // Creamos los atributos
    private String isbn;
    private String titulo;
    private String genero;
    private int annoPublicacion;
    private int numPaginas;

    // Creamos el constructor
    public LibroV2(String isbn, String titulo, String genero, int annoPublicacion, int numPaginas) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.genero = genero;
        this.annoPublicacion = annoPublicacion;
        this.numPaginas = numPaginas;
    }

    // Creamos los get y set
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnnoPublicacion() {
        return annoPublicacion;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    // Creamos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibroV2 libro = (LibroV2) o;
        return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("ISBN: %s, Título: %s, Género: %s, Año publicación: %d, Número de páginas: %d",
                this.isbn, this.titulo, this.genero, this.annoPublicacion, this.numPaginas);
    }
}