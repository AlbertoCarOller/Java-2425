package Ejercicio8;

public class Medico extends Tripulante implements Curable {

    // Creamos el constructor
    public Medico(String nombre) {
        super(nombre);
    }

    // Implementamos el método para curar
    @Override
    public void curar(Tripulante tripulante) throws NaveEspacialException {
        Curable.super.curar(tripulante);
        System.out.println(super.getNombre() + " ha curado a " + tripulante.getNombre());
    }
}