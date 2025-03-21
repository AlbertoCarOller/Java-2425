import java.time.Year;

public abstract class JugueteMadera extends Juguete {

    // Creamos los atributos
    private String origenMadera;
    private Year anoTala;
    private static final Year ANO_ACTUAL = Year.now();
    private static final Year ANO_MIN = Year.of(2000);

    // Hacemos el constructor
    public JugueteMadera(String nombre, String marca, String origenMadera, Year anoTala) throws JugueteExcepcion {
        super(nombre, marca);
        this.origenMadera = origenMadera;
        setAnoTala(anoTala);
    }

    // Creamos los get y set
    public String getOrigenMadera() {
        return origenMadera;
    }

    private void setOrigenMadera(String origenMadera) {
        this.origenMadera = origenMadera;
    }

    public Year getAnoTala() {
        return anoTala;
    }

    private void setAnoTala(Year anoTala) throws JugueteExcepcion {
        if (anoTala.isAfter(ANO_ACTUAL) || anoTala.isBefore(ANO_MIN)) {
            throw new JugueteExcepcion("Los años no son correctos");
        }
        this.anoTala = anoTala;
    }
}