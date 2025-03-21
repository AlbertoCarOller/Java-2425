package Boletin1.Ejercicio8;

public class Coche extends Vehiculo {

    // Creamos un atributo exclusivo que es el combustible
    private final Combustible combustible;

    // Creamos el constructor
    public Coche(String matricula, int numDiasAlquilados, Gama gama, Combustible combustible) {
        super(matricula, numDiasAlquilados, gama);
        this.combustible = combustible;
    }

    // Implementamos el método calcular total adaptado a coche
    public double calcularTotal() {
        return (super.gama.getPrecioBase() + combustible.getPrecioAnadido()) * numDiasAlquilados;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + ",combustible=" + combustible;
    }
}