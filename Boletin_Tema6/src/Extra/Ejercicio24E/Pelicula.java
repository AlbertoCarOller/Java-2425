package Extra.Ejercicio24E;

import java.util.Objects;

public class Pelicula {
    // Creamos los atributos
    private String codigo;
    private String titulo;
    private String genero;
    private double duracion;

    // Creamos el constructor
    public Pelicula(String codigo, String titulo, String genero, double duracion) throws PeliculaException {
        this.codigo = codigo;
        setTitulo(titulo);
        setGenero(genero);
        setDuracion(duracion);
    }

    // Creamos los get y set
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws PeliculaException {
        if (!titulo.matches("^\\p{L}\\p{L}+(\\s\\p{L}{2,})*\\s?(\\d{1,3})?(\\s\\p{L}{2,})*$")) {
            throw new PeliculaException("El título no es válido");
        }
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) throws PeliculaException {
        if (!genero.matches("^\\p{L}{2,}(\\s\\p{L}{2,})*$")) {
            throw new PeliculaException("");
        }
        this.genero = genero;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) throws PeliculaException {
        if (duracion < 1) {
            throw new PeliculaException("La película " + this.getTitulo() + " tiene una duración inválida");
        }
        this.duracion = duracion;
    }

    // Hacemos un equals por el código de la película
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(codigo, pelicula.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Pelicula{" +
                "codigo='" + codigo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", duracion=" + duracion +
                '}';
    }
}