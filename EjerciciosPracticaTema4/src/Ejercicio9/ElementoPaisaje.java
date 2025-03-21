package Ejercicio9;

import java.util.Objects;

public abstract class ElementoPaisaje implements Conectable {

    // Hacemos los atributos
    private String nombre;
    private int valor;

    // Creamos el constructor
    public ElementoPaisaje(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    private void setValor(int valor) {
        this.valor = valor;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementoPaisaje that = (ElementoPaisaje) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s\nValor: %d", this.nombre, this.valor);
    }

    // Implementamos el método conectable de la interfaz
    @Override
    public void conectar(ElementoPaisaje elementoPaisaje) {
        System.out.println("El elemento " + this.nombre + " se ha conectado con el elemento " + elementoPaisaje.nombre);
    }
}