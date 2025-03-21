package Boletin2.Ejercicio2;

public class CirculoMovible extends Circulo implements Animable{

    // Creamos el cosntructor
    public CirculoMovible(double radio) {
        super(radio);
    }

    @Override
    public void animar() {
        System.out.println("Animando");
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando circulo movible");
    }
}