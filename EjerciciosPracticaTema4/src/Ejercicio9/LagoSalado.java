package Ejercicio9;

public class LagoSalado extends Lago {

    // Creamos los atributos
    private int sanilidad;
    private boolean especiesEndemicas;

    // Creamos el constructor
    public LagoSalado(String nombre, int sanilidad, boolean especiesEndemicas) throws ElementoException {
        super("Lago Salado " + nombre, 40);
        setSanilidad(sanilidad);
        this.especiesEndemicas = especiesEndemicas;
    }

    // Creamos los get y set
    public int getSanilidad() {
        return sanilidad;
    }

    private void setSanilidad(int sanilidad) throws ElementoException {
        if (sanilidad < 10 || sanilidad > 70) {
            throw new ElementoException("Los lagos salados no pueden tener menos de 10 de sanilidad ni más de 70");
        }
        this.sanilidad = sanilidad;
    }

    public boolean isEspeciesEndemicas() {
        return especiesEndemicas;
    }

    private void setEspeciesEndemicas(boolean especiesEndemicas) {
        this.especiesEndemicas = especiesEndemicas;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + "\nSanilidad: " + this.sanilidad + "\nEspecies endémicas: " + this.especiesEndemicas;
    }
}