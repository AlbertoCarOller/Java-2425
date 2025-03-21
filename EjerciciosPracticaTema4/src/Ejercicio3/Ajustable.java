package Ejercicio3;

public interface Ajustable {

    // Hacemos un método
    default void ajustar(int ajuste) throws DispositivoExcepcion {
        if (ajuste < 1 || ajuste > 10) {
            throw new DispositivoExcepcion("El valor mínimo para el ajuste debe ser de 1 y un máximo de 10");
        }
    }
}