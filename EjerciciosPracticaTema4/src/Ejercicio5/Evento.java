package Ejercicio5;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Evento {

    // Hacemos los atributos
    private String nombre;
    private LocalDateTime fecha;
    private String ubicacion;
    private int cantidadPersonas;
    private double precioEntrada;
    private static final int AFORO_MAXIMO = 500000;

    // Hacemos el constructor
    public Evento(String nombre, LocalDateTime fecha, String ubicacion, double precioEntrada) throws EventoException {
        this.nombre = nombre;
        setFecha(fecha);
        this.ubicacion = ubicacion;
        this.cantidadPersonas = 0;
        setPrecioEntrada(precioEntrada);
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    private void setFecha(LocalDateTime fecha) throws EventoException {
        if (fecha.isBefore(LocalDateTime.now())) {
            throw new EventoException("El evento no puede ser antes de la fecha actual");
        }
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    private void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    private void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    private void setPrecioEntrada(double precioEntrada) throws EventoException {
        if (precioEntrada < 0) {
            throw new EventoException("No te vamos a dar dinero");
        }
        this.precioEntrada = precioEntrada;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Evento{" +
                "nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", ubicacion='" + ubicacion + '\'' +
                ", cantidadPersonas=" + cantidadPersonas +
                ", precioEntrada=" + precioEntrada +
                '}';
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(nombre, evento.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un método para registrar a los asistentes del evento
    public void registrarAsistentes(int numSistentes) throws EventoException {
        if (numSistentes > AFORO_MAXIMO) {
            throw new EventoException("El número de asistentes sobrepasa el aforo máximo");
        }

        if (numSistentes < 1) {
            throw new EventoException("No puede haber menos de un asistente en un evento");
        }
        this.cantidadPersonas = numSistentes;
    }
}