package Ejercicio18;

import java.util.ArrayList;
import java.util.List;

public class PeliculaComedia extends Pelicula implements Adaptable {

    // Creamos los atributos
    private int chistePorMinuto;
    private List<String> actoresFamosos;
    private static final double PORCENTAJE_EXTRA = 0.1;

    // Creamos el constructor
    public PeliculaComedia(String titulo, double duracion, double costoBase, int chistePorMinuto) throws PeliculaException {
        super(titulo, duracion, costoBase);
        this.chistePorMinuto = chistePorMinuto;
        this.actoresFamosos = new ArrayList<>();
    }

    // Hacemos los get y set
    public int getChistePorMinuto() {
        return chistePorMinuto;
    }

    protected void setChistePorMinuto(int chistePorMinuto) {
        this.chistePorMinuto = chistePorMinuto;
    }

    public List<String> getActoresFamosos() {
        return actoresFamosos;
    }

    private void setActoresFamosos(List<String> actoresFamosos) {
        this.actoresFamosos = actoresFamosos;
    }

    @Override
    public double calcularCostoTotal() {
        return super.getCostoBase() + (super.getCostoBase() * PORCENTAJE_EXTRA);
    }

    @Override
    public void adaptar() {
        this.chistePorMinuto++;
    }
}