package Extra.Ejercicio15;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Medicion {
    // Creamos los atributos
    private double valorRegistrado;
    private LocalDateTime fechaMuestra;

    // Creamos el constructor
    public Medicion(double valorRegistrado, LocalDateTime fechaMuestra) throws EstacionException {
        this.valorRegistrado = valorRegistrado;
        setFechaMuestra(fechaMuestra);
    }

    // Hacemos los get y set
    public double getValorRegistrado() {
        return valorRegistrado;
    }

    public void setValorRegistrado(double valorRegistrado) {
        this.valorRegistrado = valorRegistrado;
    }

    public LocalDateTime getFechaMuestra() {
        return fechaMuestra;
    }

    public void setFechaMuestra(LocalDateTime fechaMuestra) throws EstacionException {
        if (fechaMuestra.isAfter(LocalDateTime.now())) {
            throw new EstacionException("La fecha de la muestra no puede ser posterior a la fecha actual");
        }
        this.fechaMuestra = fechaMuestra;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Valor registrado: %f, Fecha muestra: %s", this.valorRegistrado,
                this.fechaMuestra.format(DateTimeFormatter.ofPattern("yy/MM/dd, HH:mm")));
    }
}