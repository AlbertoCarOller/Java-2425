package Boletin2.Ordenador;

public class Microprocesador {

    // Atributos
    private String marca;
    private String modelo;
    private int numeroDeNucleos;
    private float velocidadBase;
    private String socket;

    // Generamos el constructor vacío
    public Microprocesador() {
    }

    public String getSocket() {
        return socket;
    }

    // Imprimimos todos los atributos de la clase 'Microprocesador'
    @Override
    public String toString() {
        return "Microprocesador{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", numeroDeNucleos=" + numeroDeNucleos +
                ", velocidadBase=" + velocidadBase +
                ", socket='" + socket + '\'' +
                '}';
    }

    // Hacemos los set para poder darle valores al solicitárselos al usuario
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setNumeroDeNucleos(int numeroDeNucleos) {
        this.numeroDeNucleos = numeroDeNucleos;
    }

    public void setVelocidadBase(float velocidadBase) throws PlacaBaseException {

        if (velocidadBase < 0) {

            throw new PlacaBaseException("La velocidad no puede ser negativa");
        }
        this.velocidadBase = velocidadBase;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getNumeroDeNucleos() {
        return numeroDeNucleos;
    }

    public float getVelocidadBase() {
        return velocidadBase;
    }
}