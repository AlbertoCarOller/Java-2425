package Extra.Ejercicio3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Avistamiento {

    // Creamos los atributos
    private String nombreEspecie;
    private String lugar;
    private LocalDate fecha;

    // Creamos el constructor
    public Avistamiento(String nombreEspecie, String lugar, LocalDate fecha) throws AvistamientoException {
        setNombreEspecie(nombreEspecie);
        setLugar(lugar);
        setFecha(fecha);
    }

    // Creamos los get y set
    public String getNombreEspecie() {
        return nombreEspecie;
    }

    private void setNombreEspecie(String nombreEspecie) throws AvistamientoException {
        if (!Character.isUpperCase(nombreEspecie.charAt(0))) {
            throw new AvistamientoException("El nombre de la especie debe estar en mayúsculas");
        }
        this.nombreEspecie = nombreEspecie;
    }

    public String getLugar() {
        return lugar;
    }

    private void setLugar(String lugar) throws AvistamientoException {
        if (!Character.isUpperCase(lugar.charAt(0))) {
            throw new AvistamientoException("La primera letra del lugar del avistamiento debe estar en mayúsculas");
        }
        this.lugar = lugar;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    private void setFecha(LocalDate fecha) throws AvistamientoException {
        if (fecha.isAfter(LocalDate.now())) {
            throw new AvistamientoException("La fecha de registro no puede ser mayor a la fecha actual");
        }
        this.fecha = fecha;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre especie: %s, Lugar: %s, Fecha: %s", this.nombreEspecie, this.lugar,
                this.fecha.format(DateTimeFormatter.ofPattern("yy/MM/dd")));
    }
}