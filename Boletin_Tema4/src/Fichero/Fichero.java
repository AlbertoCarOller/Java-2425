package Fichero;

import java.time.LocalDate;

public abstract class Fichero {

    // Creamos los atributos
    private String nombre;
    private LocalDate fechaCreacion;

    // Creamos el constructor
    public Fichero(String nombre) {
        this.nombre = nombre;
        this.fechaCreacion = LocalDate.now();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /* Creamos un método getTamano que nos va a devolver el tamaño "en bytes", lo guardamos en un long por el gran
     * tamaño que podría ocupar, lo hacemos abstracto ya que en la clase padre no podemos calcular el tamaño, el método
     * lo definiremos en las clases hijas y se calcularán los tamaños de la forma correspondiente */
    public abstract long getTamano();

    // Hacemos un toString que nos va a mostrar la información básica, final para que no se pueda modificar en las subclases
    @Override
    public final String toString() {
        return "Fichero{" +
                "nombre='" + nombre + '\'' +
                "tamaño=" + getTamano() +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}