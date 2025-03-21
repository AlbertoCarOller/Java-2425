package Boletin2.Ordenador;

public class Ordenador {

    // Atributos / componentes del ordenador
    private PlacaBase placaBase;
    private Microprocesador microprocesador;
    private DiscoDuro discoDuro;
    private TarjetaGrafica tarjetaGrafica;

    // Hacemos el constructor
    public Ordenador(PlacaBase placaBase, Microprocesador microprocesador, DiscoDuro discoDuro,
                     TarjetaGrafica tarjetaGrafica) {

        this.placaBase = placaBase;
        this.microprocesador = microprocesador;
        this.discoDuro = discoDuro;
        this.tarjetaGrafica = tarjetaGrafica;
    }

    // Imprimimos las clases con sus atributos por pantalla
    @Override
    public String toString() {
        return "Ordenador{" +
                "placaBase=" + placaBase +
                ", microprocesador=" + microprocesador +
                ", discoDuro=" + discoDuro +
                ", tarjetaGrafica=" + tarjetaGrafica +
                '}';
    }

    public TarjetaGrafica getTarjetaGrafica() {
        return tarjetaGrafica;
    }

    public DiscoDuro getDiscoDuro() {
        return discoDuro;
    }

    public Microprocesador getMicroprocesador() {
        return microprocesador;
    }
}
