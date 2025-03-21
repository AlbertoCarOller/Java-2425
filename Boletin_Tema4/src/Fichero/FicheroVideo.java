package Fichero;

public class FicheroVideo extends FicheroBinario implements Reproducible {

    // Creamos los atributos
    private int duracion;

    // Creamos el constructor
    public FicheroVideo(String nombre, Byte[] informacion, int duracion) {
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

    // Implementamos el método
    @Override
    public void reproducir() {
        System.out.println("Reproduciendo " + super.getNombre());
    }
}