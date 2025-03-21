package Boletin1.Ejercicio8;

import java.util.Comparator;

public class Furgoneta extends Vehiculo implements Comparator<Furgoneta> {

    // Creamos un atributo exclusivo de la furgoneta
    private final double MASA_MAXIMA_AUTORIZADA;

    // Creamos el constructor
    public Furgoneta(String matricula, int numDiasAlquilados, Gama gama, double MASA_MAXIMA_AUTORIZADA) {
        super(matricula, numDiasAlquilados, gama);
        this.MASA_MAXIMA_AUTORIZADA = MASA_MAXIMA_AUTORIZADA;
    }

    // Implementamos el método en la clase hija
    public double calcularTotal() {
        return (super.gama.getPrecioBase() + (MASA_MAXIMA_AUTORIZADA * 0.5)) * numDiasAlquilados;
    }

    // Implementamos un comparator
    @Override
    public int compare(Furgoneta o1, Furgoneta o2) {
        if (o1.MASA_MAXIMA_AUTORIZADA < o2.MASA_MAXIMA_AUTORIZADA) {
            return -1;
        }
        if (o1.MASA_MAXIMA_AUTORIZADA > o2.MASA_MAXIMA_AUTORIZADA) {
            return 1;
        }
        return 0;
    }

    // Hacemos un un toString

    @Override
    public String toString() {
        return super.toString() + ",Masa máxima autorizada=" + this.MASA_MAXIMA_AUTORIZADA;
    }
}