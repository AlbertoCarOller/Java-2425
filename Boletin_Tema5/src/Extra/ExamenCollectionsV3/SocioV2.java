package Extra.ExamenCollectionsV3;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SocioV2 {

    // Creamos los atributos
    private int idSocio;
    private String nombre;
    private List<LibroV2> librosPrestados;
    private static int valorId = 0;
    private static final int MAX_PRESTADOS = 5;

    // Creamos el constructor
    public SocioV2(String nombre) {
        this.idSocio = ++valorId;
        this.nombre = nombre;
        this.librosPrestados = new LinkedList<>();
    }

    // Hacemos los get y set
    public int getIdSocio() {
        return idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public List<LibroV2> getLibrosPrestados() {
        return librosPrestados;
    }

    public void tomarPrestado(LibroV2 libro) throws BibliotecaException {
        if (librosPrestados.contains(libro) || librosPrestados.size() == MAX_PRESTADOS) {
            throw new BibliotecaException("No se puede tomar el libro");
        }
        librosPrestados.add(libro);
    }

    public void devolverLibro(LibroV2 libro) throws BibliotecaException {
        if (!librosPrestados.contains(libro)) {
            throw new BibliotecaException("El libro no se encuentra");
        }
        librosPrestados.remove(libro);
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocioV2 socio = (SocioV2) o;
        return idSocio == socio.idSocio;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idSocio);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("ID: %d, Nombre: %s, Libros prestados: %s", this.idSocio, this.nombre, this.librosPrestados);
    }
}