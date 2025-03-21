package Ejercicio3;

import java.time.Year;

public class Ordenador extends DispositivoEscritorio implements Ajustable {

    // Creamos los atributos
    private int memoriaRAM;

    // Hacemos el constructor
    public Ordenador(String modelo, String marca, Year anoFabricacion, int consumoEnergetico, String materialChasis, int memoriaRAM) throws DispositivoExcepcion {
        super(modelo, marca, anoFabricacion, consumoEnergetico, materialChasis);
        setMemoriaRAM(memoriaRAM);
    }

    // Hacemos los get y set
    public int getMemoriaRAM() {
        return memoriaRAM;
    }

    private void setMemoriaRAM(int memoriaRAM) throws DispositivoExcepcion {
        if (memoriaRAM < 1) {
            throw new DispositivoExcepcion("La memoria RAM no puede ser menor a 1");
        }
        this.memoriaRAM = memoriaRAM;
    }

    // Implementamos el método
    @Override
    public void ajustar(int ajuste) throws DispositivoExcepcion {
        Ajustable.super.ajustar(ajuste);
        this.memoriaRAM = this.memoriaRAM + ajuste;
        System.out.println("Ajustando memoria RAM");
    }
}