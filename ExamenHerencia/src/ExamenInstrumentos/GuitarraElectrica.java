package ExamenInstrumentos;

public class GuitarraElectrica extends Instrumento implements Portable, Amplificable {

    // Creamos el atributo peso
    private double peso;

    // Creamos el constructor
    public GuitarraElectrica(String nombre, Material material, boolean afinado, double peso) throws InstrumentoException {
        super(nombre, material, Familia.CUERDA, afinado);
        setPeso(peso);
    }

    // Creamos los get y set
    public double getPeso() {
        return peso;
    }

    private void setPeso(double peso) throws InstrumentoException {
        if (peso < 0) {
            throw new InstrumentoException("Un instrumento no puede pesar menos de 0 kg");
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

    /**
     * Este método mostrará por pantalla que se ha podido conectar dicho instrumento
     */
    @Override
    public void conectarAmplificador() {
        System.out.println(super.getNombre() + " se ha conectado al amplificador");
    }

    /**
     * Este método mostrará por pantalla que se ha ajustado el volumen a X de dicho
     * instrumento
     */
    @Override
    public void ajustarVolumen() {
        System.out.println("Se ha ajustado el volumen de " + super.getNombre() + " ha " + ((int) (Math.random() * 10) + 1));
    }
}