import java.time.Year;

public class PiezaLego extends JuguetePlastico implements Apilable {

    // Creamos los atributos
    private int longitud;
    private String color;

    // Creamos el constructor
    public PiezaLego(String nombre, int longitud, String color) throws JugueteExcepcion {
        super(nombre, "Lego", Plastico.AVS);
        setLongitud(longitud);
        this.color = color;
    }

    // Hacemos los get y los set
    public int getLongitud() {
        return longitud;
    }

    private void setLongitud(int longitud) throws JugueteExcepcion {
        if (longitud < 1) {
            throw new JugueteExcepcion("La pieza no puede medir menos que 1");
        }
        this.longitud = longitud;
    }

    public String getColor() {
        return color;
    }

    private void setColor(String color) {
        this.color = color;
    }

    @Override
    public void apilar(Juguete juguete) throws JugueteExcepcion {
        Apilable.super.apilar(juguete);
        System.out.println("Apilando pieza de lego");
    }
}