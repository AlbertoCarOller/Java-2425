package Ejercicio3;

import java.time.Year;

public abstract class DispositivoMovil extends Dispositivo {

    // Creamos los atributos
    private double peso;
    private SistemaOperativo sistemaOperativo;

    // Hacemos el constructor
    public DispositivoMovil(String modelo, String marca, Year anoFabricacion, double peso, SistemaOperativo sistemaOperativo) throws DispositivoExcepcion {
        super(modelo, marca, anoFabricacion);
        setPeso(peso);
        this.sistemaOperativo = sistemaOperativo;
    }

    // Hacemos los get y set
    public double getPeso() {
        return peso;
    }

    private void setPeso(double peso) throws DispositivoExcepcion {
        if (peso < 0) {
            throw new DispositivoExcepcion("El dispositivo no puede pesar en negativo");
        }
        this.peso = peso;
    }

    public SistemaOperativo getSistemaOperativo() {
        return sistemaOperativo;
    }

    private void setSistemaOperativo(SistemaOperativo sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }
}