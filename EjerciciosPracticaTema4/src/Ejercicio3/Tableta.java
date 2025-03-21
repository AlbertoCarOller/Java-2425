package Ejercicio3;

import java.time.Year;

public class Tableta extends DispositivoMovil implements Recargable {

    // Creamos los atributos
    private boolean conectividadSIM;

    // Hacemos el constructor
    public Tableta(String modelo, String marca, Year anoFabricacion, double peso, SistemaOperativo sistemaOperativo, boolean conectividadSIM) throws DispositivoExcepcion {
        super(modelo, marca, anoFabricacion, peso, sistemaOperativo);
        this.conectividadSIM = conectividadSIM;
    }

    // Hacemos los get y set
    public boolean isConectividadSIM() {
        return conectividadSIM;
    }

    private void setConectividadSIM(boolean conectividadSIM) {
        this.conectividadSIM = conectividadSIM;
    }

    @Override
    public void mostrarCarga() {
        System.out.println("Quedan 2 horas de carga para " + super.getMarca() + " " + super.getModelo());
    }
}