package Extra.Ejercicio4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Expedicion {

    // Creamos los atributos
    private String nombre;
    private String lugar;
    private LocalDate fechaRealizacion;

    // Creamos el constructor
    public Expedicion(String nombre, String lugar, LocalDate fechaRealizacion) throws HallazgoException {
        setNombre(nombre);
        setLugar(lugar);
        this.fechaRealizacion = fechaRealizacion;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws HallazgoException {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new HallazgoException("La primera letra del nombre de una expedición debe estar en mayúsculas");
        }
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    private void setLugar(String lugar) throws HallazgoException {
        if (!Character.isUpperCase(lugar.charAt(0))) {
            throw new HallazgoException("Como es un lugar su nombre debe empezar en mayúsculas");
        }
        this.lugar = lugar;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    private void setFechaRealizacion(LocalDate fechaRealizacion) throws HallazgoException {
        if (fechaRealizacion.isAfter(LocalDate.now())) {
            throw new HallazgoException("La fecha de la realización no puede ser posterior a la actual");
        }
        this.fechaRealizacion = fechaRealizacion;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expedicion that = (Expedicion) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Lugar: %s, Fecha realización: %s", this.nombre, this.lugar,
                this.fechaRealizacion.format(DateTimeFormatter.ofPattern("yy/MM/dd")));
    }
}