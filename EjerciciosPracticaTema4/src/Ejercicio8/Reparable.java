package Ejercicio8;

public interface Reparable {

    // Hacemos un método
    default void reparar(NaveEspacial naveEspacial) throws NaveEspacialException {
        if (naveEspacial.getSalud() == 350) {
            throw new NaveEspacialException("La nave está al máximo de salud");
        }
        if ((naveEspacial.getSalud() + 10) > 350) {
            naveEspacial.setSalud(350);

        } else {
            naveEspacial.setSalud(naveEspacial.getSalud() + 10);
        }
    }
}