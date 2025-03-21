package Fichero;

public class FicheroEjecutable extends FicheroBinario implements Analizable{

    // Creamos el atributo
    private int permisos;

    // Creamos el constructor
    public FicheroEjecutable(String nombre, Byte[] informacion, int permisos) {
        super(nombre, informacion);
        this.permisos = permisos;
    }

    // Hacemos los get y set
    public int getPermisos() {
        return permisos;
    }

    public void setPermisos(int permisos) {
        this.permisos = permisos;
    }

    // Implementamos el método analizar
    public void analizar() {
        System.out.println("Analizando el fichero " + this.getNombre());
    }
}