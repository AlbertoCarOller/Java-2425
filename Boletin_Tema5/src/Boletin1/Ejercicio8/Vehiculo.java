package Boletin1.Ejercicio8;

import java.util.Objects;

public abstract class Vehiculo implements Comparable<Vehiculo> {

    // Atributos
    protected final String matricula;
    protected int numDiasAlquilados;
    protected final Gama gama;

    // Creamos el constructor
    public Vehiculo(String matricula, int numDiasAlquilados, Gama gama) {
        this.matricula = matricula;
        this.numDiasAlquilados = numDiasAlquilados;
        this.gama = gama;
    }

    // Hacemos los get y set
    public String getMatricula() {
        return matricula;
    }

    public int getNumDiasAlquilados() {
        return numDiasAlquilados;
    }

    public Gama getGama() {
        return gama;
    }

    public void setNumDiasAlquilados(int numDiasAlquilados) {
        this.numDiasAlquilados = numDiasAlquilados;
    }

    // Comrpobamos si son iguales dos vehículos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricula);
    }

    // Hacemos un método para calcular el precio del vehículo
    public double calcularTotal() {
        return numDiasAlquilados * gama.getPrecioBase();
    }

    @Override
    public int compareTo(Vehiculo o) {
        return Double.compare(this.gama.getPrecioBase(), o.gama.getPrecioBase());
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula='" + matricula + '\'' +
                ", numDiasAlquilados=" + numDiasAlquilados +
                ", gama=" + gama +
                '}';
    }
}