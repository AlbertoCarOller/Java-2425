package Fichero;

public abstract class FicheroBinario extends Fichero {

    // Creamos los atributos
    private Byte[] informacion;

    // Creamos el constructor
    public FicheroBinario(String nombre, Byte[] informacion) {
        super(nombre);
        this.informacion = informacion;
    }

    // Hacemos los get y set
    public Byte[] getInformacion() {
        return informacion;
    }

    public void setInformacion(Byte[] informacion) {
        this.informacion = informacion;
    }

    public long getTamano() {
        return informacion.length;
    }
}