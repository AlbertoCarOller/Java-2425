package Ejercicio18;

import java.util.Objects;

public abstract class Pelicula {

    // Creamos los atributos
    private String titulo;
    private double duracion;
    private double costoBase;

    // Creamos el constructor
    public Pelicula(String titulo, double duracion, double costoBase) throws PeliculaException {
        this.titulo = titulo;
        setDuracion(duracion);
        setCostoBase(costoBase);
    }

    // Hacemos los get y set
    public String getTitulo() {
        return titulo;
    }

    private void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getDuracion() {
        return duracion;
    }

    private void setDuracion(double duracion) throws PeliculaException {
        if (duracion < 1) {
            throw new PeliculaException("La duración no puede ser menos de 1 hora");
        }
        this.duracion = duracion;
    }

    public double getCostoBase() {
        return costoBase;
    }

    private void setCostoBase(double costoBase) throws PeliculaException {
        if (costoBase < 1) {
            throw new PeliculaException("El costo base no puede ser menor a 1");
        }
        this.costoBase = costoBase;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(titulo, pelicula.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Título: %s, Duración: %f, Coste base: %f", this.titulo, this.duracion, this.costoBase);
    }

    // Hacemos un método para calcular
    public abstract double calcularCostoTotal();
}