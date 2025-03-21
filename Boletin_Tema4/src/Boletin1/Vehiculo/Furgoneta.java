package Boletin1.Vehiculo;

public class Furgoneta extends Vehiculo{

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
}