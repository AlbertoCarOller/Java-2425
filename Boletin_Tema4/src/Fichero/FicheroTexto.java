package Fichero;

public abstract class FicheroTexto extends Fichero implements Leible {

    // Creamos los atributos
    private String[] parrafos;

    // Creamos el constructor
    public FicheroTexto(String nombre, String[] parrafos) {
        super(nombre);
        this.parrafos = parrafos;
    }

    // Hacemos los get y set
    public String[] getParrafos() {
        return parrafos;
    }

    public void setParrafos(String[] parrafos) {
        this.parrafos = parrafos;
    }

    public long getTamano() {
        long acumulador = 0;

        for (int i = 0; i < parrafos.length; i++) {

            acumulador += parrafos[i].length();
        }
        return acumulador;
    }

    public String leer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parrafos.length; i++) {
            sb.append(parrafos[i]);
        }
        return sb.toString();
    }
}