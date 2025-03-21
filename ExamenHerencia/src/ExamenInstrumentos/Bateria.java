package ExamenInstrumentos;

public class Bateria extends Instrumento {

    // Creamos el constructor
    public Bateria(String nombre, Material material, boolean afinado) {
        super(nombre, material, Familia.PERCUSION, afinado);
    }

    @Override
    public void afinar() {
        if (!super.isAfinado()) {
            super.setAfinado(true);
        }
    }

    @Override
    public void tocar() {
        if (!super.isAfinado()) {
            System.out.println("El instrumento " + super.getNombre() + " no se puede tocar, necesita ser afinado");

        } else {
            System.out.println(super.getNombre() + " está siendo tocado");
        }
    }
}