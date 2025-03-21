package Ejercicio17;

import java.util.Objects;

public abstract class Juego {

    // Creamos los atributos
    private String titulo;
    private String estudio;
    private double costoDesarrollo;

    // Creamos el constructor
    public Juego(String titulo, String estudio, double costoDesarrollo) throws JuegoException {
        this.titulo = titulo;
        this.estudio = estudio;
        setCostoDesarrollo(costoDesarrollo);
    }

    // Hacemos los get y set
    public String getTitulo() {
        return titulo;
    }

    private void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstudio() {
        return estudio;
    }

    private void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public double getCostoDesarrollo() {
        return costoDesarrollo;
    }

    protected void setCostoDesarrollo(double costoDesarrollo) throws JuegoException {
        if (costoDesarrollo < 0) {
            throw new JuegoException("El costo no puede ser menor a 0");
        }
        this.costoDesarrollo = costoDesarrollo;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Juego juego = (Juego) o;
        return Objects.equals(titulo, juego.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Titulo: %s, Estudio: %s, Costo: %f", this.titulo, this.estudio, this.costoDesarrollo);
    }

    // Hacemos un método para calcular el costo total
    public abstract double calcularCostoTotal();
}