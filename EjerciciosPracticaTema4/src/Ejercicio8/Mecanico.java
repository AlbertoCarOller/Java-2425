package Ejercicio8;

public class Mecanico extends Tripulante implements Reparable {

    // Creamos el constructor
    public Mecanico(String nombre) {
        super(nombre);
    }

    // Implemnetamos el método de reparar

    @Override
    public void reparar(NaveEspacial naveEspacial) throws NaveEspacialException {
        Reparable.super.reparar(naveEspacial);
        System.out.println("El mecánico " + super.getNombre() + " ha reparado la nave " + naveEspacial.getNombre());
    }
}