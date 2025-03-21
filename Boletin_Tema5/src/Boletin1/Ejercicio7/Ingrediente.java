package Boletin1.Ejercicio7;

import java.util.Objects;

public class Ingrediente {

    // Creamos los atributos
    private String nombre;
    private int cantidad;

    // Creamos el constructor
    public Ingrediente(String nombre) {
        this.nombre = nombre;
        this.cantidad = 0;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    protected void setCantidad(int cantidad) throws IngredienteException {
        if (cantidad < 0) {
            throw new IngredienteException("La cantidad no puede ser negativa");
        }
        this.cantidad = cantidad;
    }

    // Hacemos el toString
    @Override
    public String toString() {
        return String.format("El nombre es %s y su cantidad es de %d", this.nombre, this.cantidad);
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente that = (Ingrediente) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}