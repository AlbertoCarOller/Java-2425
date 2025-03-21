package Fichero;

public class FicheroAudio extends FicheroBinario implements Reproducible{

    // Creamos los atributos
    private int duracion;

    // Creamos el constructor
    public FicheroAudio(String nombre, Byte[] informacion, int duracion) {
        super(nombre, informacion);
        this.duracion = duracion;
    }

    // Hacemos los get y set
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    // Implementamos el método para reproducir el fichero
    @Override
    public void reproducir() {
        System.out.println("Reproduciendo " + super.getNombre());
    }
}