package Boletin2.Ejercicio2;

public class Rectangulo extends Forma implements Dibujable{

    // Creamos los atributos
    private double alto;
    private double ancho;

    // Creaos el constructor
    public Rectangulo(double alto, double ancho) {
        this.alto = alto;
        this.ancho = ancho;
    }

    @Override
    public double calcularArea() {
        return alto * ancho;
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando rectángulo");
    }
}