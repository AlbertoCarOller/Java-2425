package BoletinExtra.Ejercicio2;

public class EncendibleApp {
    public static void main(String[] args) {

        // Creamos la lámpara y el el televisor
        Encendible lampara = new Lampara();
        Encendible televisor = new Televisor();

        // Llamámos los métodos
        lampara.encender();
        lampara.apagar();

        televisor.encender();
        televisor.apagar();
    }
}