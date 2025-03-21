package Ejercicio14;

import java.util.Objects;

public class Cientifico {

    // Creamos los atributos
    private String nombre;
    private boolean autorizacion;

    // Creamos el constructor
    public Cientifico(String nombre, boolean autorizacion) {
        this.nombre = nombre;
        this.autorizacion = autorizacion;
    }

    // Hacemos un get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(boolean autorizacion) {
        this.autorizacion = autorizacion;
    }

    // Hacemos un equals-
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cientifico that = (Cientifico) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Autorización: %b", this.nombre, this.autorizacion);
    }
}