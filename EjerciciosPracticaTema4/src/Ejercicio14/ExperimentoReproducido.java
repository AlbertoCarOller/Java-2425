package Ejercicio14;

public class ExperimentoReproducido extends Experimento implements Reproducible {

    // Creamos un atributo que sea el número de veces que se ha repito
    private int numReproducciones;

    // Creamos el constructor
    public ExperimentoReproducido(String nombre, Cientifico cientifico) {
        super(nombre, cientifico);
        this.numReproducciones = 0;
    }

    // Hacemos los get y set
    public int getNumReproducciones() {
        return numReproducciones;
    }

    private void setNumReproducciones(int numReproducciones) {
        this.numReproducciones = numReproducciones;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo el experimento " + super.getNombre());
        this.numReproducciones++;
    }
}