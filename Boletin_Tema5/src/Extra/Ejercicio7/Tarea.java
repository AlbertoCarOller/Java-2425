package Extra.Ejercicio7;

import java.util.Objects;

public class Tarea implements Comparable<Tarea> {

    // Creamos los atributos
    private String nombre;
    private String descripcion;
    private Prioridad prioridad;
    private Tarea tarea;
    private boolean solucionada;

    // Creamos el constructor
    public Tarea(String nombre, String descripcion, Prioridad prioridad, Tarea tarea) throws TareaException {
        setNombre(nombre);
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.tarea = tarea;
        this.solucionada = false;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws TareaException {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new TareaException("La primera letra del nombre debe estar en mayúsculas");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    private void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Tarea getTarea() {
        return tarea;
    }

    protected void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public boolean isSolucionada() {
        return solucionada;
    }

    protected void setSolucionada(boolean solucionada) {
        this.solucionada = solucionada;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        return Objects.equals(nombre, tarea.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        if (this.tarea == null) {
            return String.format("Nombre: %s, Descripción: %s, Prioridad: %s, Tarea que lo bloquea: Ninguna, Solucionada: %b",
                    this.nombre, this.descripcion, this.prioridad, this.solucionada);
        } else {
            return String.format("Nombre: %s, Descripción: %s, Prioridad: %s, Tarea que lo bloquea: %s, Solucionada: %b",
                    this.nombre, this.descripcion, this.prioridad, this.tarea.getNombre(), this.solucionada);
        }
    }

    @Override
    public int compareTo(Tarea o) {
        return Integer.compare(this.getPrioridad().getValorNumerico(), o.prioridad.getValorNumerico());
    }
}