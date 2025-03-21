package ExamenInstrumentos;

public class GuitarraAcustica extends Instrumento implements Portable {

    // Creamos el atributo peso
    private double peso;

    // Creamos el constructor
    public GuitarraAcustica(String nombre, Material material, boolean afinado, double peso) throws InstrumentoException {
        super(nombre, material, Familia.CUERDA, afinado);
        setPeso(peso);
    }

    // Hacemos los get y set
    public double getPeso() {
        return peso;
    }

    private void setPeso(double peso) throws InstrumentoException {
        if (peso < 0) {
            throw new InstrumentoException("El peso no puede ser menor a 0");
        }
        this.peso = peso;
    }

    // Modificamos el toString
    @Override
    public String toString() {
        return super.toString() + ", Peso: " + this.peso;
    }

    /**
     * Comprobamos si afinado está a true, en caso de que no, lo pasamos a true, para simular
     * que el instrumento está afinado
     *
     * @throws InstrumentoException
     */
    @Override
    public void afinar() {
        if (!super.isAfinado()) {
            super.setAfinado(true);
        }
    }

    /**
     * Comprobamos si afinado está a true, si es así el instrumento podrá tocarse, en caso de que no
     * se mostrará por pantalla
     *
     * @throws InstrumentoException
     */
    @Override
    public void tocar() {
        if (!super.isAfinado()) {
            System.out.println("El instrumento " + super.getNombre() + " no se puede tocar, necesita ser afinado");

        } else {
            System.out.println(super.getNombre() + " está siendo tocado");
        }
    }

    /**
     * En este método comprobamos si el peso es menor o igual a 3, en este caso
     * devolveremos true porque será fácil de trasportar, en caso contrario
     * devolverá false
     *
     * @return
     */
    @Override
    public boolean mostrarFacilidad() {
        if (this.peso <= 3) {
            return true;
        }
        return false;
    }
}