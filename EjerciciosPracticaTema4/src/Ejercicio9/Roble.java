package Ejercicio9;

public class Roble extends Arbol {

    // Creamos los atributos
    private double altura;
    private boolean frutos;

    // Creamos el constructor
    public Roble(String nombre, double altura, boolean frutos) throws ElementoException {
        super("Roble " + nombre, 60);
        setAltura(altura);
        this.frutos = frutos;
    }

    // Hacemos los get y set
    public double getAltura() {
        return altura;
    }

    private void setAltura(double altura) throws ElementoException {
        if (altura < 0.5 || altura > 5) {
            throw new ElementoException("El roble no puede medir menos de medio metro ni más de 5 metros");
        }
        this.altura = altura;
    }

    public boolean isFrutos() {
        return frutos;
    }

    private void setFrutos(boolean frutos) {
        this.frutos = frutos;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + "\nAltura: " + this.altura + "\nFrutos: " + this.frutos;
    }
}