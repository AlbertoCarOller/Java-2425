package BoletinExtra.Ejercicio2;

public class Televisor implements Encendible {

    // Implementamos los métodos de la interfaz
    @Override
    public void encender() {
        System.out.println("El televisor está encendido");
    }

    @Override
    public void apagar() {
        System.out.println("El televisor está apagado");
    }
}