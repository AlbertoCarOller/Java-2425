package Ejercicio18;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PeliculaAccion extends Pelicula implements Verificable, Adaptable {

    // Creamos los atributos
    private int nivelIntensidad;
    private List<String> efectosEspeciales;
    private static final int COSTE_ADICIONAL = 10;
    private int numVerificaciones;
    private static final double PORCENTAJE_EXTRA = 0.05;

    // Creamos el constructor
    public PeliculaAccion(String titulo, double duracion, double costoBase, int nivelIntensidad) throws PeliculaException {
        super(titulo, duracion, costoBase);
        setNivelIntensidad(nivelIntensidad);
        this.efectosEspeciales = new ArrayList<>();
        this.numVerificaciones = 0;
    }

    // Hacemos los get y set
    public int getNivelIntensidad() {
        return nivelIntensidad;
    }

    private void setNivelIntensidad(int nivelIntensidad) throws PeliculaException {
        if (nivelIntensidad < 1) {
            throw new PeliculaException("La película no puede tener una intensidad menor a 1");
        }
        this.nivelIntensidad = nivelIntensidad;
    }

    public List<String> getEfectosEspeciales() {
        return efectosEspeciales;
    }

    private void setEfectosEspeciales(List<String> efectosEspeciales) {
        this.efectosEspeciales = efectosEspeciales;
    }

    public int getNumVerificaciones() {
        return numVerificaciones;
    }

    public void setNumVerificaciones(int numVerificaciones) {
        this.numVerificaciones = numVerificaciones;
    }

    @Override
    public double calcularCostoTotal() {
        return super.getCostoBase() + (this.numVerificaciones * COSTE_ADICIONAL) + (super.getCostoBase() * PORCENTAJE_EXTRA);
    }

    // Modificamos el toString
    @Override
    public String toString() {
        return super.toString() + ", Nivel de intensidad: " + this.nivelIntensidad + ", Efectos especiales: " + this.efectosEspeciales
                + ", Número de verificaciones: " + this.numVerificaciones;
    }

    @Override
    public boolean verificar() {
        Random probabilidad = new Random();
        this.numVerificaciones++;
        return probabilidad.nextBoolean();
    }

    @Override
    public void adaptar() {
        this.nivelIntensidad++;
    }
}