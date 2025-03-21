public class VehiculoPlastico extends JuguetePlastico {

    // Creamos los atributos
    private int numRuedas;

    // Creamos el constructor
    public VehiculoPlastico(String nombre, String marca, int numRuedas) throws JugueteExcepcion {
        super(nombre, marca, Plastico.PVC);
        setNumRuedas(numRuedas);
    }

    // Creamos los get y set
    public int getNumRuedas() {
        return numRuedas;
    }

    private void setNumRuedas(int numRuedas) throws JugueteExcepcion {
        if (numRuedas < 2) {
            throw new JugueteExcepcion("El número de ruedas no puede ser menor a 2");
        }
        this.numRuedas = numRuedas;
    }
}