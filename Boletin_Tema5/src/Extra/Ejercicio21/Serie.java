package Extra.Ejercicio21;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Serie {

    // Creamos los atributos
    private String titulo;
    private String genero;
    private int cantidadTemporadas;
    private List<Usuario> suscriptores;

    // Creamos el constructor
    public Serie(String titulo, String genero, int cantidadTemporadas) {
        this.titulo = titulo;
        this.genero = genero;
        this.cantidadTemporadas = cantidadTemporadas;
        this.suscriptores = new LinkedList<>();
    }

    // Creamos los get
    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getCantidadTemporadas() {
        return cantidadTemporadas;
    }

    protected List<Usuario> getSuscriptores() {
        return suscriptores;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return Objects.equals(titulo, serie.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Título: %s, Género: %s, Cantidad temporadas: %d, Suscriptores: %s", this.titulo,
                this.genero, this.cantidadTemporadas, this.suscriptores);
    }

    // Hacemos un método auxiliar para añadir un usuario a la serie
    public void anadirUsuario(Usuario usuario) throws SerieException {
        if (suscriptores.contains(usuario)) {
            throw new SerieException("El usuario ya está siguiendo la serie");
        }
        suscriptores.add(usuario);
    }

    // Hacemos un método auxiliar para borrar un usuario de la lista de suscriptores de una serie
    public void eliminarUsuario(Usuario usuario) throws SerieException {
        if (!suscriptores.contains(usuario)) {
            throw new SerieException("EL usuario no se ha encontrado entre los suscriptores de esta serie");
        }
        suscriptores.remove(usuario);
    }

    // Hacemos un método que va a devolver la cantidad de suscriptores de una serie
    public int numSuscriptores() {
        return this.getSuscriptores().size();
    }
}