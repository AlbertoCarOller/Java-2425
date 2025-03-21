package Ejercicio4;

import java.time.Year;

public class Modelo3D extends Obra implements Interactuable {

    // Creamos los atributos
    private int numPoligonos;
    private int tamano;
    private int numInteracciones;
    private Interaccion interaccion;

    // Hacemos el constructor
    public Modelo3D(String titulo, String autor, Year anoCreacion, double valorEuros, int numPoligonos, int tamano,
                    int numInteracciones, Interaccion interaccion) throws ObraException {
        super(titulo, autor, anoCreacion, valorEuros);
        setNumPoligonos(numPoligonos);
        setTamano(tamano);
        this.numInteracciones = numInteracciones;
        this.interaccion = interaccion;
    }

    // Hacemos los get y set
    public int getNumPoligonos() {
        return numPoligonos;
    }

    public void setNumPoligonos(int numPoligonos) throws ObraException {
        if (numPoligonos < 1) {
            throw new ObraException("El número de polígonos no puede ser menor a 1");
        }
        this.numPoligonos = numPoligonos;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) throws ObraException {
        if (tamano < 1) {
            throw new ObraException("El tamaño no puede ser menor a 0 MB");
        }
        this.tamano = tamano;
    }

    public int getNumInteracciones() {
        return numInteracciones;
    }

    private void setNumInteracciones(int numInteracciones) {
        this.numInteracciones = numInteracciones;
    }

    public Interaccion getInteraccion() {
        return interaccion;
    }

    private void setInteraccion(Interaccion interaccion) {
        this.interaccion = interaccion;
    }

    @Override
    public void registrarInteraciones() {
        System.out.println("El modelo 3D " + super.getTitulo() + " tiene " + this.numInteracciones + " interacciones");
    }

    @Override
    public void interactuar() {
        System.out.println("El modelo 3D " + super.getTitulo() + " tiene la siguiente interacción: " + this.interaccion.name().toLowerCase());
    }
}