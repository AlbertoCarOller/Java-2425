package Ejercicio3;

import java.time.Year;

public class Monitor extends DispositivoEscritorio implements Ajustable {

    // Creamos los atributos
    private double tamanoPulgadas;

    // Hacemos el constructor
    public Monitor(String modelo, String marca, Year anoFabricacion, int consumoEnergetico, String materialChasis, double tamanoPulgadas) throws DispositivoExcepcion {
        super(modelo, marca, anoFabricacion, consumoEnergetico, materialChasis);
        setTamanoPulgadas(tamanoPulgadas);
    }

    // Hacemos los get y set
    public double getTamanoPulgadas() {
        return tamanoPulgadas;
    }

    private void setTamanoPulgadas(double tamanoPulgadas) throws DispositivoExcepcion {
        if (tamanoPulgadas < 0) {
            throw new DispositivoExcepcion("El tamaño no puede medir menos de 1 pulgada");
        }
        this.tamanoPulgadas = tamanoPulgadas;
    }

    // Implementamos el método de la interfaz
    @Override
    public void ajustar(int ajuste) throws DispositivoExcepcion {
        Ajustable.super.ajustar(ajuste);
        System.out.println("Ajustando brillo a " + ajuste);
    }
}