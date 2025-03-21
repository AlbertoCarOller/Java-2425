package BoletinExtra.Ejercicio4;

public class Coche extends Vehiculo implements Movible, Cargable {

    // Creamos el constuctor
    public Coche(String nombre) {
        super(nombre);
    }

    // Implementamos los métodos de las dos interfaces
    @Override
    public void cargar() {
        System.out.println("Cargando pasajeros en el coche " + this.getNombre());
    }

    @Override
    public void descargar() {
        System.out.println("Descargando pasajeros en el coche " + this.getNombre());
    }

    @Override
    public void acelerar() {
        System.out.println("Acelerando el coche " + this.getNombre());
    }

    @Override
    public void frenar() {
        System.out.println("Frenando el coche " + this.getNombre());
    }
}