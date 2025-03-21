package Boletin2.Ejercicio2;

public class Circulo extends Forma implements Dibujable{

    // Creamos los atributos
    private double radio;

    // Creamos el constructor
    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando círculo");
    }
}