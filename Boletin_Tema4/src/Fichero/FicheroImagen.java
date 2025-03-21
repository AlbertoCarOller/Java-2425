package Fichero;

public class FicheroImagen extends FicheroBinario {

    // Creamos los atributos
    private Formato formato;

    // Creamos el constructor
    public FicheroImagen(String nombre, Byte[] informacion, Formato formato) {
        super(nombre, informacion);
        this.formato = formato;
    }

    // Creamos los get y set
    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }
}