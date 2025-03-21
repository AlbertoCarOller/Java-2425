package BoletinExtra.Ejercicio4;

public class Bicicleta extends Vehiculo implements Movible {

    // Creamos el constructor
    public Bicicleta(String nombre) {
        super(nombre);
    }

    // Implementamos los métodos de la interfaz
    @Override
    public void acelerar() {
        System.out.println("Acelerando bicicleta " + this.getNombre());
    }

    @Override
    public void frenar() {
        System.out.println("Frenando bicicleta " + this.getNombre());
    }
}