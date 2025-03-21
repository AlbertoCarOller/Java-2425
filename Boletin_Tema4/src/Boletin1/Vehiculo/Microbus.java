package Boletin1.Vehiculo;

public class Microbus extends Vehiculo {

    // Creamos una variable exclusiva que son los números de asientos disponibles
    private int plazasDisponibles;
    private static final int PRECIO_PLAZA = 5;

    // Creamos el constructor
    public Microbus(String matricula, int numDiasAlquilados, Gama gama, int plazasDisponibles) {
        super(matricula, numDiasAlquilados, gama);
        this.plazasDisponibles = plazasDisponibles;
    }

    // Implementamos el método en la clase hija
    public double calcularTotal() {
        return (super.gama.getPrecioBase() + (plazasDisponibles * PRECIO_PLAZA)) * numDiasAlquilados;
    }
}