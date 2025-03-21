package Ejercicio10;

public interface Regador {

    // Hacemos un método para regar un cultivo
    void regar(String nombreCultivo, Granja granja) throws CultivoException;
}