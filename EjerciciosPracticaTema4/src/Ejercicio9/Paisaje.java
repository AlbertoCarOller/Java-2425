package Ejercicio9;

import java.util.Arrays;

public class Paisaje {

    // Creamos los atributos
    private ElementoPaisaje[] elementosPaisajes;
    private static final int TAMANO_MAX = 6;

    // Creamos el constructor
    public Paisaje() {
        this.elementosPaisajes = new ElementoPaisaje[TAMANO_MAX];
    }

    // Hacemos los get y set
    public ElementoPaisaje[] getElementosPaisajes() {
        return elementosPaisajes;
    }

    private void setElementosPaisajes(ElementoPaisaje[] elementosPaisajes) {
        this.elementosPaisajes = elementosPaisajes;
    }

    // Hacemos un método para añadir un elemento al paisaje
    public void anadirElemento(ElementoPaisaje elementoPaisaje) throws ElementoException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < elementosPaisajes.length && !esIgual; i++) {
            if (elementoPaisaje.equals(elementosPaisajes[i])) {
                esIgual = true;
            }
            if (elementosPaisajes[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            elementosPaisajes[hayEspacio] = elementoPaisaje;

        } else {
            throw new ElementoException("No se puede añadir el elemento al paisaje");
        }
    }

    // Hacemos un método para calcular el valor del paisaje
    public int calcularValorPaisaje() {
        int valorTotal = 0;
        for (ElementoPaisaje elementoPaisaje : elementosPaisajes) {
            if (elementoPaisaje != null) {
                valorTotal += elementoPaisaje.getValor();
            }
        }
        return valorTotal;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("El paisaje: %s", Arrays.toString(this.elementosPaisajes));
    }
}