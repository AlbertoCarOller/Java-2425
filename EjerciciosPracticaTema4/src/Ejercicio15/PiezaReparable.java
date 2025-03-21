package Ejercicio15;

public class PiezaReparable extends Pieza implements Reparable {

    private boolean ok;

    // Creamos el constructor
    public PiezaReparable(String nombre, int valor, boolean ok) throws TallerExeption {
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
            throw new TallerExeption("La pieza" + super.getNombre() + "está bien, no puede ser reparada");
        }
        this.setOk(true);
        super.setValor(super.getValor() + ((int) (Math.random() * 50) + 10));
        System.out.println(super.getNombre() + " ha sido reparada");
    }
}