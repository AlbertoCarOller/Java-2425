package Ejercicio15;

import java.util.Random;

public class PiezaTesteable extends Pieza implements Testeable {

    // Creamos el constructor
    public PiezaTesteable(String nombre, int valor) throws TallerExeption {
        super(nombre,valor);
    }

    @Override
    public boolean testear() throws TallerExeption {
        Random probabilidad = new Random();
        super.setValor(super.getValor() + 4);
        return probabilidad.nextBoolean();
    }
}