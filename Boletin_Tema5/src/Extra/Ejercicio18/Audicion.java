package Extra.Ejercicio18;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Audicion {

    // Creamos los atributos
    private double puntuaje;
    private LocalDate fecha;

    // Creamos el constructor
    public Audicion(LocalDate fecha) throws EstudianteMusicalException {
        this.puntuaje = Math.random() * 100;
        setFecha(fecha);
    }

    // Creamos los get y set
    public double getPuntuaje() {
        return puntuaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    private void setFecha(LocalDate fecha) throws EstudianteMusicalException {
        if (fecha.isAfter(LocalDate.now())) {
            throw new EstudianteMusicalException("La fecha no puede ser posterior a la actual");
        }
        this.fecha = fecha;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Puntuaje: %f, Fecha: %s", this.puntuaje,
                this.fecha.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }
}