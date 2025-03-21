package Boletin2.Ejercicio2;

public class Canvas {
    public static void main(String[] args) {

        // Creamos las formas
        Rectangulo rectangulo = new Rectangulo(3.4, 4.2);
        Circulo circulo = new Circulo(7.8);
        CirculoMovible circuloMovible = new CirculoMovible(5.6);
        Linea linea = new Linea();

        /* Creamos el array de formas, IMPORTANTE, no se puede añadir un objeto que se haya creado a partir del
         * contrato ya que en este caso el array es de formas, no de Dibujable ni Animable */
        Forma[] formas = {rectangulo, circulo, circuloMovible, linea};
        dibujaYAnimaFormas(formas);

    }

    // Hacemos un método para animar y dibujar las formas
    public static void dibujaYAnimaFormas(Forma[] formas) {

        // Recorre el array de las formas
        for (int i = 0; i < formas.length; i++) {

            // Si en la posición i encuentra una forma que sea dibujable, llama al método
            if (formas[i] instanceof Dibujable dibujable) {
                dibujable.dibujar();
            }

            // Si en la posición i encuentra una forma que sea animable, llama al método
            if (formas[i] instanceof Animable) {
                Animable a = (Animable) formas[i];
                a.animar();
            }
        }
    }
}