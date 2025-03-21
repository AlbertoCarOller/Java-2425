package Extra.Ejercicio1;

import java.util.Objects;

public class Documento implements Comparable<Documento> {

    // Creamos los atributos
    private String titulo;
    private String contenido;
    private Prioridad ordenPrioridad;
    private String nombreSolicitante;

    // Creamos el constructor
    public Documento(String titulo, String contenido, Prioridad ordenPrioridad, String nombreSolicitante) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.ordenPrioridad = ordenPrioridad;
        this.nombreSolicitante = nombreSolicitante;
    }

    // Creamos los get y set
    public String getTitulo() {
        return titulo;
    }

    private void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    private void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Prioridad getOrdenPrioridad() {
        return ordenPrioridad;
    }

    private void setOrdenPrioridad(Prioridad ordenPrioridad) {
        this.ordenPrioridad = ordenPrioridad;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    private void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return Objects.equals(titulo, documento.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Título: %s\nContenido: %s\nPrioridad: %s\nNombre solicitante: %s", this.titulo, this.contenido,
                this.ordenPrioridad, this.nombreSolicitante);
    }

    @Override
    public int compareTo(Documento o) {
        return Integer.compare(this.ordenPrioridad.getPrioridad(), o.ordenPrioridad.getPrioridad());
    }
}