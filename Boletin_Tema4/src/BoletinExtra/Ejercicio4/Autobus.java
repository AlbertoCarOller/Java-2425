package BoletinExtra.Ejercicio4;

public class Autobus extends Vehiculo implements Movible, Cargable {

    // Creamos el constructor
    public Autobus(String nombre) {
        super(nombre);
    }

    // Implementamos los métodos de las interfaces
    @Override
    public void cargar() {
        System.out.println("Cargando el autobús " + this.getNombre());
    }

    @Override
    public void descargar() {
        System.out.println("Descargando el autobús " + this.getNombre());
    }

    @Override
    public void acelerar() {
        System.out.println("Acelerando el autobús " + this.getNombre());
    }

    @Override
    public void frenar() {
        System.out.println("Frenando el autobús " + this.getNombre());
    }
}