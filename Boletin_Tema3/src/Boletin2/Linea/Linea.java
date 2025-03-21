package Boletin2.Linea;

import java.util.Objects;

public class Linea {

    // Atributos
    private Punto puntoA;
    private Punto puntoB;

    // Constructor que recibe los puntos A y B de la clase 'Punto'
    public Linea(Punto puntoA, Punto puntoB) {
        this.puntoA = puntoA;
        this.puntoB = puntoB;
    }

    // Constructor que le da unos valores a los atributos de la clase 'Punto'
    public Linea() {

        // Tenemos que crear los objetos para poder inicializarlos con los valores que queremos
        this.puntoA = new Punto(0.0, 0.0);
        this.puntoB = new Punto(0.0, 0.0);
    }

    // Se generan los get para acceder al valor de los atributos fuera de esta clase
    public Punto getPuntoA() {
        return puntoA;
    }

    public Punto getPuntoB() {
        return puntoB;
    }

    // Hacemos los set para modificar el valor de estos atributos
    public void setPuntoB(Punto puntoB)
    {
        this.puntoB = puntoB;
    }

    public void setPuntoA(Punto puntoA) {
        this.puntoA = puntoA;
    }

    // Comparamos los puntos de las líneas
    @Override
    public boolean equals(Object o) {
        // Si el objeto actual es el mismo que el que se pasa por parámetros devuelve true
        if (this == o) return true;
        // Si el objeto es null o no está en la misma clase devuelve false
        if (o == null || getClass() != o.getClass()) return false;
        // Se transforma el objeto de la clase 'Object' a la clase 'Linea'
        Linea linea = (Linea) o;
        // Se comparan los puntos de las líneas
        return puntoA.equals(linea.puntoA) && puntoB.equals(linea.puntoB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(puntoA, puntoB);
    }

    // Movemos los puntos a la derecha
    public void moverDerecha (double distancia) throws LineaException {

        if (distancia < 0) {

            throw new LineaException("No puede ser negativa la distancia");
        }

        // Sumamos los valores a X
        System.out.println("Has movido la línea");
        this.puntoA.setX(this.puntoA.getX() + distancia);
        this.puntoB.setX(this.puntoB.getX() + distancia);
    }

    // Movemos los puntos a la izquierda
    public void moverIzquierda(double distancia) throws LineaException {

        if (distancia < 0) {

            throw new LineaException("No puede ser negativa la distancia");
        }

        // Restamos los valores a x
        System.out.println("Has movido la línea");
        this.puntoA.setX(this.puntoA.getX() - distancia);
        this.puntoB.setX(this.puntoB.getX() - distancia);
    }

    // Movemos los puntos hacia arriba
    public void moverArriba(double distancia) throws LineaException {

        if (distancia < 0) {

            throw new LineaException("No puede ser negativa la distancia");
        }
        // Sumamos los valores y
        System.out.println("Has movido la línea");
        this.puntoA.setY(this.puntoA.getY() + distancia);
        this.puntoB.setY(this.puntoB.getY() + distancia);
    }

    public void moverAbajo(double distancia) throws LineaException {

        if (distancia < 0) {

            throw new LineaException("No puede ser negativa la distancia");
        }

        // Restamos a los valores y
        System.out.println("Has movido la línea");
        this.puntoA.setY(this.puntoA.getY() - distancia);
        this.puntoB.setY(this.puntoB.getY() - distancia);
    }

    @Override
    public String toString() {
        return "Linea{" +
                "puntoA=" + puntoA +
                ", puntoB=" + puntoB +
                '}';
    }
}