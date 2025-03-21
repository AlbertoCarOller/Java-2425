package Ejercicio10;

public interface Cosechador {

    // Hacemos un método para poder cosechar un cultivo
    void cosechar(String nombreCultivo, Granja granja) throws CultivoException;
}