package Ejercicio8;

import java.util.Objects;

public abstract class Tripulante {

    // Hacemos los atributos
    private String nombre;
    private int salud;

    // Hacemos el constructor
    public Tripulante(String nombre) {
        this.nombre = nombre;
        this.salud = 100;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalud() {
        return salud;
    }

    protected void setSalud(int salud) {
        this.salud = salud;
    }

    // Hacemos el toString
    @Override
    public String toString() {
        return String.format("El nombre del tripulante es %s y su salud es de %d", this.nombre, this.salud);
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tripulante that = (Tripulante) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}