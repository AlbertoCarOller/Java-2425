package Ejercicio17;

import java.util.Random;

public class JuegoDeporte extends Juego implements Multijugable {

    // Creamos los atributos
    private TDeporte tipoDeporte;
    private String licenciaOficial;
    private static final double PORCENTAJE_EXTRA = 0.1;

    // Creamos el constructor
    public JuegoDeporte(String titulo, String estudio, double costoDesarrollo, TDeporte tipoDeporte, String licenciaOficial) throws JuegoException {
        super(titulo, estudio, costoDesarrollo);
        this.tipoDeporte = tipoDeporte;
        this.licenciaOficial = licenciaOficial;
    }

    // Hacemos los get y set
    public TDeporte getTipoDeporte() {
        return tipoDeporte;
    }

    private void setTipoDeporte(TDeporte tipoDeporte) {
        this.tipoDeporte = tipoDeporte;
    }

    public String getLicenciaOficial() {
        return licenciaOficial;
    }

    private void setLicenciaOficial(String licenciaOficial) {
        this.licenciaOficial = licenciaOficial;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + ", Tipo de deporte: " + this.tipoDeporte + ", Licencia oficial: " + this.licenciaOficial;
    }

    @Override
    public double calcularCostoTotal() {
        return super.getCostoDesarrollo() + (super.getCostoDesarrollo() * PORCENTAJE_EXTRA);
    }

    @Override
    public void conectarAServidor() {
        Random probabilidad = new Random();
        if (probabilidad.nextBoolean()) {
            System.out.println("El juego " + super.getTitulo() + " se ha conectado al servidor");

        } else {
            System.out.println("El juego " + super.getTitulo() + " no se ha podido conectar al servidor");
        }
    }
}