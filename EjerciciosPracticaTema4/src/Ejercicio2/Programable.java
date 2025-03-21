package Ejercicio2;

public interface Programable {

    // Creamos el método, le damos código ya que es default
    default void programar(int cuentaAtras) throws ElectrodomesticoException {
        if (cuentaAtras < 1) {
            throw new ElectrodomesticoException("La cuenta atrás no puede ser menor a 1");
        }
    }
}