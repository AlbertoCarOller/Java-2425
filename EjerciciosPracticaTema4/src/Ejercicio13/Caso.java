package Ejercicio13;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Caso {

    // Creamos los atributos
    private String nombre;
    private Dificultad nivelDificultad;
    private LocalDate fechaAsignacion;
    private boolean resuelto;

    // Creamos el constructor
    public Caso(String nombre, Dificultad nivelDificultad) {
        this.nombre = nombre;
        this.nivelDificultad = nivelDificultad;
        this.fechaAsignacion = LocalDate.now();
        this.resuelto = false;
    }

    // Hacemos los get y set
    public Dificultad getNivelDificultad() {
        return nivelDificultad;
    }

    private void setNivelDificultad(Dificultad nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    protected void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caso caso = (Caso) o;
        return Objects.equals(nombre, caso.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Creamos un toString
    @Override
    public String toString() {
        return String.format("Nivel dificultad: %s, Fecha asignación: %s, Resuelto: %b", this.nivelDificultad,
                fechaAsignacion.format(DateTimeFormatter.ofPattern("yy/MM/dd")), this.resuelto);
    }
}