package Ejercicio15;

import java.util.Random;

public class PiezaReparableTesteable extends Pieza implements Reparable, Testeable {

    private boolean ok;

    // Creamos el constructor
    public PiezaReparableTesteable(String nombre, int valor, boolean ok) throws TallerExeption {
        super(nombre, valor);
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public void reparar() throws TallerExeption {
        if (this.isOk()) {
            throw new TallerExeption("No se puede reparar " + super.getNombre());
        }
        this.setOk(true);
        super.setValor(super.getValor() + ((int) (Math.random() * 50) + 10));
        System.out.println(super.getNombre() + " ha sido reparada");
    }

    @Override
    public boolean testear() throws TallerExeption {
        Random probabilidad = new Random();
        super.setValor(super.getValor() + 4);
        return probabilidad.nextBoolean();
    }
}