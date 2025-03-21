package Boletin2.Ejercicio2;

public class Linea extends Forma {

    // Heredamos en método calcularArea de la clase padre Forma
    @Override
    public double calcularArea() {
        /* Esta excepción es para cuando sabemos que un método no va a funcionar o no se va a poder realizar,
         * no hace falta colocar el 'throws...' */
        throw new UnsupportedOperationException("Línea no sabe calcular el área");
    }
}