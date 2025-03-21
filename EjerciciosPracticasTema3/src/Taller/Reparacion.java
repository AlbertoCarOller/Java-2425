package Taller;

public class Reparacion {

    // Creamos los atributos
    private String nombre;
    private double coste;
    private int tiempo;

    // Creamos el constructor
    public Reparacion(String nombre, double coste, int tiempo) {
        this.nombre = nombre;
        this.coste = coste;
        this.tiempo = tiempo;
    }

    // Hacemos get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Reparacion{" +
                "nombre='" + nombre + '\'' +
                ", coste=" + coste +
                ", tiempo=" + tiempo +
                '}';
    }
}