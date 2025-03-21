package Ejercicio8;

public interface Curable {

    // Hacemos un método para curar a otro tripulante
    default void curar(Tripulante tripulante) throws NaveEspacialException {
        if (this.equals(tripulante)) {
            throw new NaveEspacialException("No te puedes curar a ti mismo");
        }
        if (tripulante.getSalud() == 100) {
            throw new NaveEspacialException("Ya está al máximo de vida");
        }

        if ((tripulante.getSalud() + 10) > 100) {
            tripulante.setSalud(100);

        } else {
            tripulante.setSalud(tripulante.getSalud() + 10);
        }
    }
}