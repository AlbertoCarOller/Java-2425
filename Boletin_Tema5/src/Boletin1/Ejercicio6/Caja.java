package Boletin1.Ejercicio6;

import java.util.Objects;

public class Caja {

    // Creamos los atributos
    private int numCaja;
    private static int valorNumCaja = 1;
    private boolean abierta;

    // Creamos el constructor
    public Caja() {
        this.numCaja = valorNumCaja++;
        this.abierta = true;
    }

    // Creamos los get y set
    public int getNumCaja() {
        return numCaja;
    }

    private void setNumCaja(int numCaja) {
        this.numCaja = numCaja;
    }

    public boolean isAbierta() {
        return abierta;
    }

    protected void setAbierta(boolean abierta) {
        this.abierta = abierta;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("El número de caja %d, abierta: %b", this.numCaja, this.abierta);
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caja caja = (Caja) o;
        return numCaja == caja.numCaja;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numCaja);
    }
}