package ExamenInstrumentos;

public class Flauta extends Instrumento implements Portable {

    // Creamos el atributo peso
    private double peso;

    // Creamos el constructor
    public Flauta(String nombre, Material material, boolean afinado, double peso) throws InstrumentoException {
        super(nombre, material, Familia.VIENTO, afinado);
        setPeso(peso);
    }

    // Hacemos los get y set
    public double getPeso() {
        return peso;
    }

    private void setPeso(double peso) throws InstrumentoException {
        if (peso < 0) {
            throw new InstrumentoException("El instrumento no puede pesar menos de 0");
        }
        this.peso = peso;
    }

    // Modificamos el toString
    @Override
    public String toString() {
        return super.toString() + ", Peso: " + this.peso;
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

    @Override
    public boolean mostrarFacilidad() {
        if (this.peso <= 3) {
            return true;
        }
        return false;
    }
}