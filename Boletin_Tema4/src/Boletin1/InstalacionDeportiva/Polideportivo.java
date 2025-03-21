package Boletin1.InstalacionDeportiva;

public class Polideportivo implements Edificio, InstalacionDeportiva{

    // Creamos atributos
    private double superficie;
    private String tipoInstalacion;

    // Creamos el constructor
    public Polideportivo(double superficie, String tipoInstalacion) {
        this.superficie = superficie;
        this.tipoInstalacion = tipoInstalacion;
    }

    @Override
    public double getSuperficieEdificio() {
        return superficie;
    }

    @Override
    public String getTipoInstalacion() {
        return tipoInstalacion;
    }
}
