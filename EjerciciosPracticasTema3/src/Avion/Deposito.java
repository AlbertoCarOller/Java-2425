package Avion;

public class Deposito {

    // Creamos los atributos
    private int capacidadActual;
    public static final int MAX_CAPACIDAD = 500;

    // Creamos el constructor
    public Deposito() {
        this.capacidadActual = MAX_CAPACIDAD;
    }

    // Creamos los get y set
    public int getCapacidadActual() {
        return capacidadActual;
    }

    protected void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    // Hacemos un toSTring
    @Override
    public String toString() {
        return "Deposito{" +
                "capacidadActual=" + capacidadActual +
                '}';
    }
}