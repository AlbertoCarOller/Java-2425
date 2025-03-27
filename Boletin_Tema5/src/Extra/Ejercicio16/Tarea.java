package Extra.Ejercicio16;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Tarea {
    // Creamos los atributos
    private String titulo;
    private String descripcion;
    private Estado estado;
    private LocalDateTime fechaFinalizacion;
    private LocalDateTime fechaAsignacion;

    // Creamos el constructor
    public Tarea(String titulo, String descripcion, LocalDateTime fechaAsignacion) throws ProyectoException {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = Estado.PENDIENTE;
        setFechaAsignacion(fechaAsignacion);
    }

    // Hacemos los get y set
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDateTime fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    private void setFechaAsignacion(LocalDateTime fechaAsignacion) throws ProyectoException {
        if (fechaAsignacion.isAfter(LocalDateTime.now())) {
            throw new ProyectoException("La fecha de asignación no puede ser posterior a la actual");
        }
        this.fechaAsignacion = fechaAsignacion;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        return Objects.equals(titulo, tarea.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        if (this.fechaFinalizacion != null) {
            return String.format("Título: %s, Descripción: %s, Estado: %s, Fecha finalización: %s, Fecha asignación: %s",
                    this.titulo, this.descripcion, this.estado,
                    this.fechaFinalizacion.format(DateTimeFormatter.ofPattern("yyyy/MM/dd, HH:mm")),
                    this.fechaAsignacion.format(DateTimeFormatter.ofPattern("yyyy/MM/dd, HH:mm")));

        } else {
            return String.format("Título: %s, Descripción: %s, Estado: %s, Fecha finalización: Sin finalizar, Fecha asignación: %s",
                    this.titulo, this.descripcion, this.estado,
                    this.fechaAsignacion.format(DateTimeFormatter.ofPattern("yyyy/MM/dd, HH:mm")));
        }
    }
}