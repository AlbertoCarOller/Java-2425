package Ejercicio2;

public class ElectrodomesticoPequeno extends Electrodomestico implements Programable {

    // Creamos los atributos
    private int numFunciones;
    private Corriente corriente;

    // Hacemos el constructor
    public ElectrodomesticoPequeno(String nombre, String marca, double precio, int numFunciones, Corriente energia) throws ElectrodomesticoException {
        super(nombre, marca, precio);
        setNumFunciones(numFunciones);
        this.corriente = energia;
    }

    // Hacemos los get y set
    public int getNumFunciones() {
        return numFunciones;
    }

    private void setNumFunciones(int numFunciones) throws ElectrodomesticoException {
        if (numFunciones < 1) {
            throw new ElectrodomesticoException("No puede tener menos de 1 función");
        }
        this.numFunciones = numFunciones;
    }

    public Corriente getCorriente() {
        return corriente;
    }

    private void setCorriente(Corriente corriente) {
        this.corriente = corriente;
    }

    // Implementamos el método de la interfaz
    @Override
    public void programar(int cuentaAtras) throws ElectrodomesticoException {
        Programable.super.programar(cuentaAtras);
        System.out.println("Cuenta atrás de " + cuentaAtras + " minutos activada");
    }
}