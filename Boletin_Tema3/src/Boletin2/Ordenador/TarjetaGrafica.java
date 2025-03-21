package Boletin2.Ordenador;

public class TarjetaGrafica {

    // Atributos
    private String marca;
    private String modelo;
    private int numeroDeNucleos;
    private float velocidad;
    private int cantidadDeMemoria;

    // Generamos el constructor vacío
    public TarjetaGrafica() {
    }

    // Imprimimos los atributos de la clase 'TarjetaGrafica'
    @Override
    public String toString() {
        return "TarjetaGrafica{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", numeroDeNucleos=" + numeroDeNucleos +
                ", velocidad=" + velocidad +
                ", cantidadDeMemoria=" + cantidadDeMemoria +
                '}';
    }

    // Hacemos los set para poder darle valores al solicitárselos al usuario
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCantidadDeMemoria(int cantidadDeMemoria) {
        this.cantidadDeMemoria = cantidadDeMemoria;
    }

    public void setVelocidad(float velocidad) throws PlacaBaseException {

        if (velocidad < 0) {

            throw new PlacaBaseException("La velocidad de la tarjeta gráfica bo puede ser negativa");
        }

        this.velocidad = velocidad;
    }

    public void setNumeroDeNucleos(int numeroDeNucleos) {
        this.numeroDeNucleos = numeroDeNucleos;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCantidadDeMemoria() {
        return cantidadDeMemoria;
    }

    public int getNumeroDeNucleos() {
        return numeroDeNucleos;
    }
}