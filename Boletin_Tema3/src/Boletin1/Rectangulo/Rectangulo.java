package Boletin1.Rectangulo;

import java.util.Objects;

// Creamos la clase (objeto) 'Rectangulo'
public class Rectangulo {

    // Creamos los atributos de la clase 'Rectangulo'
    private int longitud;
    private int ancho;

    // Creamos el constructor, dándole un valor predeterminado de 1 a sus dos atributos
    public Rectangulo() {
        this.longitud = 1;
        this.ancho = 1;
    }

    // Creamos el get
    public int getLongitud() {
        return longitud;
    }

    // Creamos el set con la condición de que la longitud esté entre 0 y 20
    public void setLongitud(int longitud) throws RectanguloException {

        if (longitud < 0 || longitud > 20) {

            throw new RectanguloException("La longitud no es correcta");
        }
        this.longitud = longitud;
    }

    // Creamos el get
    public int getAncho() {
        return ancho;
    }

    // Creamos el set con la condición de que la longitud esté entre 0 y 20
    public void setAncho(int ancho) {

        if (ancho > 0 && ancho < 20) {

            this.ancho = ancho;
        }
    }

    // Creamos el método que va a calcular el área del triángulo
    public int area() {

        return ancho * longitud;
    }

    // Creamos el método que va a calcular el perímetro del triángulo
    public int perimetro() {

        return  2 * (ancho + longitud);
    }

    // Utilizamos este método para poder imprimir los atributos y valores del objeto
    @Override
    public String toString() {
        return "Rectangulo{" +
                "ancho=" + ancho +
                ", longitud=" + longitud +
                '}';
    }

    // No se utiliza a partir de aquí, solo son explicaciones de los métodos 'equals' y 'hashCode'
    @Override
    public boolean equals(Object o) {
        // Si 'this' (atributo) tiene la misma referencia que el objeto que pasas, devuelve true
        if (this == o) return true;
        // Si el objeto es nulo o está en una clase diferente al otro, devuelve false
        if (o == null || getClass() != o.getClass()) return false;
        /* 'that' es el nombre que le ponemos al objeto que le vamos a dar el valor del objeto que pasamos
         por parámetros, le hacemos un casting del tipo 'Rectangulo' para comparar posteriormente sus valores */
        Rectangulo that = (Rectangulo) o;
        // Una vez casteado comparamos los atributos de los objetos
        return longitud == that.longitud && ancho == that.ancho;
    }

    /* Le da un valor a cada objeto dependiendo de la dirección de memoria en la que estén,
     si son iguales los valores, estarán en la misma dirección de memoria, este método complementa a 'equals' */ 
    @Override
    public int hashCode() {
        return Objects.hash(longitud, ancho);
    }
}