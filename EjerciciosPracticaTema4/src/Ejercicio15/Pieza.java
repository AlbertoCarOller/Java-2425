package Ejercicio15;

import java.util.Objects;

public class Pieza {

    // Creamos los atributos
    private String nombre;
    private int valor;

    // Creamos el constructor
    public Pieza(String nombre, int valor) throws TallerExeption {
        this.nombre = nombre;
        setValor(valor);
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

    public void setValor(int valor) throws TallerExeption {
        if (valor < 1) {
            throw new TallerExeption("El valor no puede ser menor a 1");
        }
        this.valor = valor;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Valor: %d", this.nombre, this.valor);
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pieza pieza = (Pieza) o;
        return Objects.equals(nombre, pieza.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}