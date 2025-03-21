package Ejercicio8;

public class Sensor {

    // Hacemos los atributos
    private String nombre;
    private int precision;

    // Hacemos el constructor
    public Sensor(String nombre, int precision) {
        this.nombre = nombre;
        this.precision = precision;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecision() {
        return precision;
    }

    private void setPrecision(int precision) {
        this.precision = precision;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("El nombre del sensor es %s y su precisión es de %d", this.nombre, this.precision);
    }
}