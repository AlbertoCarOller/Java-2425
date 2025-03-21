package Fichero;

public class FicheroTextoFormateado extends FicheroTexto implements Analizable{

    // Creamos los atributos
    private Fuente fuente;
    private int tamano;
    private String color;

    // Hacemos el constructor
    public FicheroTextoFormateado(String nombre, String[] parrafos, Fuente fuente, int tamano, String color) {
        super(nombre,parrafos);
        this.fuente = fuente;
        this.tamano = tamano;
        this.color = color;
    }

    // Hacemos los get y set
    public Fuente getFuente() {
        return fuente;
    }

    public void setFuente(Fuente fuente) {
        this.fuente = fuente;
    }

    // Implementamos el método analizar
    public void analizar() {
        System.out.println("Analizando el fichero " + this.getNombre());
    }
}