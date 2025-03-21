package Boletin1.Ejercicio1;

import java.util.Objects;

public class Alumno {

    // Creamos los atributos
    private String nombre;
    private String dni;

    // Hacemos el constructor
    public Alumno(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
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

    public void setDni(String dni) {
        this.dni = dni;
    }

    // Hacemos el equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(nombre, alumno.nombre) && Objects.equals(dni, alumno.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, dni);
    }

    // Hacemos el toString
    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}