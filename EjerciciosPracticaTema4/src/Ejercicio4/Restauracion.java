package Ejercicio4;

import java.time.LocalDate;

public class Restauracion {

    // Creamos los atributos
    private LocalDate fechaModificacion;
    private String motivo;

    // Creamos el constructor
    public Restauracion(String motivo) {
        this.fechaModificacion = LocalDate.now();
        this.motivo = motivo;
    }

    // Hacemos los get y set
    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    private void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getMotivo() {
        return motivo;
    }

    private void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    // Hacemos los toString
    @Override
    public String toString() {
        return "Restaurar{" +
                "fechaModificacion=" + fechaModificacion +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}