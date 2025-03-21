package BoletinExtra.Ejercicio3;

public class Documento implements Enviable, Imprimible {

    // Impelementamos los métodos de las dos interfaces
    @Override
    public void enviar() {
        System.out.println("Enviando el documento...");
    }

    @Override
    public void imprimir() {
        System.out.println("Imprimiendo el documento...");
    }
}