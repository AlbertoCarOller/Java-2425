package Boletin3.Ejercicio1B3;

import java.time.LocalDate;
import java.util.Objects;

public class Persona {

    // Creamos los atributos
    private String nombre;
    private String dni;
    private String telefono;
    private LocalDate fechaNacimiento;

    // Creamos el constructor
    public Persona(String nombre, String dni, String telefono) throws PersonaException {
        this.nombre = nombre;
        setDni(dni);
        setTelefono(telefono);
        this.fechaNacimiento = LocalDate.now();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws PersonaException {
        if (!dni.matches("^[0-9]{8}[A-Z]$")) {
            throw new PersonaException("El dni de " + this.getNombre() + " no es válido");
        }
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws PersonaException {
        if (!telefono.matches("^[67][0-9]{8}")) {
            throw new PersonaException("El número de telefono de " + this.getNombre() + " no es correcto");
        }
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    // Creamos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    // Creamos el toString
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}