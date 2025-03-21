package Boletin2.Linea;

import java.util.Objects;

public class Punto {

    // Atributos
    private double x;
    private double y;

    // Generamos el constructor pasándole los los atributos
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Generamos un construcor vacío
    public Punto() {
    }

    // Hacemos los get para devolver el valor
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Generamos los set para modificar el valor de estos atributos
    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Punto{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    // Comparamos si los ejes son iguales o no
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Punto punto = (Punto) o;
        return Double.compare(x, punto.x) == 0 && Double.compare(y, punto.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
