import java.time.Year;

public class InstrumentoMusicalMadera extends JugueteMadera {

    // Creamos los atributos
    private int edadMinima;

    // Creamos el constructor
    public InstrumentoMusicalMadera(String nombre, String marca, String origenMadera, Year anoTala, int edadMinima) throws JugueteExcepcion {
        super(nombre, marca, origenMadera, anoTala);
        setEdadMinima(edadMinima);
    }

    // Creamos los get y set
    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) throws JugueteExcepcion {
        if (edadMinima < 0) {
            throw new JugueteExcepcion("La edad mínima no puede ser menor que 0");
        }
        this.edadMinima = edadMinima;
    }
}
