package BoletinExtra.Ejercicio2;

public class Lampara implements Encendible {

    // Implementamos los métodos de la interfaz
    @Override
    public void encender() {
        System.out.println("La lámpara está encendida");
    }

    @Override
    public void apagar() {
        System.out.println("La lámpara está apagada");
    }
}