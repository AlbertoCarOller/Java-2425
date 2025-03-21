package Boletin1.Ejercicio6;

import java.util.Objects;

public class Cliente {

    // Creamos los atributos
    private int numeroCliente;
    private static int valorNumero = 1;

    // Creamos el constructor
    public Cliente() {
        this.numeroCliente = valorNumero++;
    }

    // Creamos los get y set
    public int getNumeroCliente() {
        return numeroCliente;
    }

    private void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("El número de cliente es %d", this.numeroCliente);
    }

    // Hademos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return numeroCliente == cliente.numeroCliente;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroCliente);
    }
}