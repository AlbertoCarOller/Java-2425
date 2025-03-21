package Restaurante;

import java.util.Objects;

public class Plato {

    // Atributos
    private String nombre;
    private double precio;
    private int tiempoPreparacion;

    // Hacemos el constructor
    public Plato(String nombre, double precio, int tiempoPreparacion) {
        this.nombre = nombre;
        this.precio = precio;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    // Hacemos un toString para imprimir la información de los platos
    @Override
    public String toString() {
        return "Restaurante.Plato{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", tiempoPreparacion=" + tiempoPreparacion +
                '}';
    }

    // Hacemos los get
    public double getPrecio() {
        return precio;
    }

    // Hacemos un método equals para comprobar que los platos no sean los mismos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plato plato = (Plato) o;
        return Objects.equals(nombre, plato.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}