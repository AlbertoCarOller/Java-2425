package Extra.Ejercicio6;

import Boletin1.Ejercicio3.PersonaExcepcion;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Persona {

    // Creamos los atributos
    private String nombre;
    private int edad;
    private LocalTime horaLlegada;

    // Creamos el constructor
    public Persona(String nombre, int edad, LocalTime horaLlegada) throws PersonaExcepcion {
        this.nombre = nombre;
        setEdad(edad);
        setHoraLlegada(horaLlegada);
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    private void setEdad(int edad) throws PersonaExcepcion {
        if (edad < 12) {
            throw new PersonaExcepcion("Una persona no puede tener menos de 12 años para acceder a un parque de atracciones");
        }
        this.edad = edad;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    private void setHoraLlegada(LocalTime horaLlegada) throws PersonaExcepcion {
        if (horaLlegada.isAfter(LocalTime.of(20, 0))) {
            throw new PersonaExcepcion("La hora de entrada no puede ser mayor a las 20:00");
        }
        this.horaLlegada = horaLlegada;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(nombre, persona.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Edad: %d, Hora de llegada: %s", this.nombre, this.edad,
                this.horaLlegada.format(DateTimeFormatter.ISO_LOCAL_TIME));
    }
}