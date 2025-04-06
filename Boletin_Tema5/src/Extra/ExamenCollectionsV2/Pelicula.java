package Extra.ExamenCollectionsV2;

import Extra.Ejercicio21.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Pelicula {

    // Creamos los atributos
    private String titulo;
    private int anoEstreno;
    private String director;
    private String genero;
    private Set<Resena> resenas;

    // Creamos el constructor
    public Pelicula(String titulo, int anoEstreno, String director, String genero) throws PeliculaException {
        this.titulo = titulo.trim();
        setAnoEstreno(anoEstreno);
        this.director = director.trim();
        this.genero = genero.trim();
        this.resenas = new HashSet<>();
    }

    // Hacemos los get y set
    public String getTitulo() {
        return titulo;
    }

    public int getAnoEstreno() {
        return anoEstreno;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }

    private void setAnoEstreno(int anoEstreno) throws PeliculaException {
        if (anoEstreno < 1975 || anoEstreno > 2025) {
            throw new PeliculaException("El año debe estar en una fecha válida");
        }
        this.anoEstreno = anoEstreno;
    }

    protected Set<Resena> getResenas() {
        return resenas;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return anoEstreno == pelicula.anoEstreno && Objects.equals(titulo, pelicula.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, anoEstreno);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Título: %s, Año estreno: %d, Director: %s, Género: %s, Reseñas: %s",
                this.titulo, this.anoEstreno, this.director, this.genero, this.resenas);
    }

    // Hacemos un método auxiliar para añadir una reseña a las películas
    public void addResena(Resena r) {
        this.resenas.add(r);
    }

    // Hacemos un método auxiliar que va a devolver la media de las reseñas de una película
    public double mediaValoracion() {
        return this.resenas.stream().mapToInt(Resena::getValoracion).average().orElse(0);
    }
}