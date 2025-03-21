package Ejercicio3;

import java.time.Year;

public class Telefono extends DispositivoMovil implements Recargable {

    // Creamos los atributos
    private int capacidadBateria;

    // Hacemos el constructor
    public Telefono(String modelo, String marca, Year anoFabricacion, double peso, SistemaOperativo sistemaOperativo, int capacidadBateria) throws DispositivoExcepcion {
        super(modelo, marca, anoFabricacion, peso, sistemaOperativo);
        setCapacidadBateria(capacidadBateria);
    }

    // Hacemos los get y set
    public int getCapacidadBateria() {
        return capacidadBateria;
    }

    private void setCapacidadBateria(int capacidadBateria) throws DispositivoExcepcion {
        if (capacidadBateria < 0 || capacidadBateria > 100) {
            throw new DispositivoExcepcion("El móvil no puede tener más de 100 bastería ni menos de 0");
        }
        this.capacidadBateria = capacidadBateria;
    }

    // Implementamos el método de la interfaz
    @Override
    public void mostrarCarga() {
        double horas = (double) capacidadBateria / 20;
        System.out.println("Quedan " + horas + " horas de batería" + " para " + super.getMarca() + " " + super.getModelo());
    }
}