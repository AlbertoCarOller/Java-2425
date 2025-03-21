package Ejercicio9;

public class LagoDulce extends Lago {

    // Creamos los atributos
    private int profundidadMaxima;
    private boolean islas;

    // Creamos el constructor
    public LagoDulce(String nombre, int profundidadMaxima, boolean islas) throws ElementoException {
        super("Lago Dulce " + nombre, 60);
        setProfundidadMaxima(profundidadMaxima);
        this.islas = islas;
    }

    // Creamos los get y set
    public int getProfundidadMaxima() {
        return profundidadMaxima;
    }

    private void setProfundidadMaxima(int profundidadMaxima) throws ElementoException {
        if (profundidadMaxima < 5 || profundidadMaxima > 50) {
            throw new ElementoException("La profundidad de un lago de agua dulce no puede ser menor a 5 metros ni mayor a 50");
        }
        this.profundidadMaxima = profundidadMaxima;
    }

    public boolean isIslas() {
        return islas;
    }

    private void setIslas(boolean islas) {
        this.islas = islas;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + "\nProfundidad Máxima: " + this.profundidadMaxima + "\nIslas: " + this.islas;
    }
}