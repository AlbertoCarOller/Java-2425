import java.time.Year;

public class FiguraMadera extends JugueteMadera implements Apilable {

    // Creamos los atributos
    private String color;
    private int numLados;

    // Creamos el constructor
    public FiguraMadera(String nombre, String marca, String origenMadera, Year anoTala, String color, int numLados) throws JugueteExcepcion {
        super(nombre, marca, origenMadera, anoTala);
        this.color = color;
        this.numLados = numLados;
    }

    // Hacemos los get y set
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumLados() {
        return numLados;
    }

    public void setNumLados(int numLados) {
        this.numLados = numLados;
    }

    @Override
    public void apilar(Juguete juguete) throws JugueteExcepcion {
        Apilable.super.apilar(juguete);
        System.out.println("Apilando figura de madera");
    }
}