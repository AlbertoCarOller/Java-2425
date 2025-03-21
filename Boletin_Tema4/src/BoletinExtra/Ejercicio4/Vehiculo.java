package BoletinExtra.Ejercicio4;

public abstract class Vehiculo {

    // Creamos los atributos
    private String nombre;

    // Creamos el constructor
    public Vehiculo(String nombre) {
        this.nombre = nombre;
    }

    // Creamos los get y los set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }
}