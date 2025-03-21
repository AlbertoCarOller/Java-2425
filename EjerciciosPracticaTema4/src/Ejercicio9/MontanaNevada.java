package Ejercicio9;

public class MontanaNevada extends Montana {

    // Creamos los atributos
    private int cantidadNieveAcumulada;
    private Flora tipoFlora;

    // Creamos el constrcutor
    public MontanaNevada(String nombre, int cantidadNieveAcumulada, Flora tipoFlora) throws ElementoException {
        super("Montaña nevada: " + nombre, 70);
        setCantidadNieveAcumulada(cantidadNieveAcumulada);
        this.tipoFlora = tipoFlora;
    }

    // Hacemos los get y set
    public int getCantidadNieveAcumulada() {
        return cantidadNieveAcumulada;
    }

    private void setCantidadNieveAcumulada(int cantidadNieveAcumulada) throws ElementoException {
        if (cantidadNieveAcumulada < 10 || cantidadNieveAcumulada > 50) {
            throw new ElementoException("No puede haber menos de 10 cm de nieve ni más de 50");
        }
        this.cantidadNieveAcumulada = cantidadNieveAcumulada;
    }

    public Flora getTipoFlora() {
        return tipoFlora;
    }

    private void setTipoFlora(Flora tipoFlora) {
        this.tipoFlora = tipoFlora;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + "\nCantidad de nieve acumulada: " + this.cantidadNieveAcumulada + "\nFlora: " + this.tipoFlora;
    }
}