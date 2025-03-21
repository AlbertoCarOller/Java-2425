package Ejercicio3;

import java.time.Year;

public abstract class DispositivoEscritorio extends Dispositivo {

    // Creamos los atributos
    private int consumoEnergetico;
    private String materialChasis;

    // Hacemos el constructor
    public DispositivoEscritorio(String modelo, String marca, Year anoFabricacion, int consumoEnergetico, String materialChasis) throws DispositivoExcepcion {
        super(modelo, marca, anoFabricacion);
        setConsumoEnergetico(consumoEnergetico);
        this.materialChasis = materialChasis;
    }

    // Hacemos los get y set
    public int getConsumoEnergetico() {
        return consumoEnergetico;
    }

    private void setConsumoEnergetico(int consumoEnergetico) throws DispositivoExcepcion {

        if (consumoEnergetico < 1) {
            throw new DispositivoExcepcion("No puede consunir menos de 1");
        }
        this.consumoEnergetico = consumoEnergetico;
    }

    public String getMaterialChasis() {
        return materialChasis;
    }

    private void setMaterialChasis(String materialChasis) {
        this.materialChasis = materialChasis;
    }
}