package EmpresaTransporte;

public class Deposito {

    // Creamos los atributos
    protected static final int MAX_CAPACIDAD = 150;
    private int capacidadActual;

    // Creamos el constructor
    public Deposito() {
        this.capacidadActual = MAX_CAPACIDAD;
    }

    // Creamos los get y los set
    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    // Hacenmos un toString
    @Override
    public String toString() {
        return "Deposito{" +
                "capacidadActual=" + capacidadActual +
                '}';
    }
}