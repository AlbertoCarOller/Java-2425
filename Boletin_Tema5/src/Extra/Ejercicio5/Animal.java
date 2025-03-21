package Extra.Ejercicio5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Animal {

    // Creamos los atributos
    private String nombreEspecie;
    private LocalDate fechaAvistamiento;
    private String zonaReserva;

    // Creamos el constructor
    public Animal(String nombreEspecie, LocalDate fechaAvistamiento, String zonaReserva) throws AnimalException {
        this.nombreEspecie = nombreEspecie;
        setFechaAvistamiento(fechaAvistamiento);
        this.zonaReserva = zonaReserva;
    }

    // Creamos los get y set
    public String getNombreEspecie() {
        return nombreEspecie;
    }

    public void setNombreEspecie(String nombreEspecie) {
        this.nombreEspecie = nombreEspecie;
    }

    public LocalDate getFechaAvistamiento() {
        return fechaAvistamiento;
    }

    public void setFechaAvistamiento(LocalDate fechaAvistamiento) throws AnimalException {
        if (fechaAvistamiento.isAfter(LocalDate.now())) {
            throw new AnimalException("La fecha no puede ser mayor a la actual");
        }
        this.fechaAvistamiento = fechaAvistamiento;
    }

    public String getZonaReserva() {
        return zonaReserva;
    }

    public void setZonaReserva(String zonaReserva) {
        this.zonaReserva = zonaReserva;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(nombreEspecie, animal.nombreEspecie);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombreEspecie);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre especie: %s, Fecha avistamiento: %S, Zona reserva: %s", this.nombreEspecie,
                this.fechaAvistamiento.format(DateTimeFormatter.ofPattern("yy/MM/dd")), this.zonaReserva);
    }
}