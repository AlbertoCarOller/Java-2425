package Ejercicio14;

import java.util.Objects;

public abstract class Experimento {

    // Creamos las características
    private int id;
    private static int idValor = 0;
    private String nombre;
    private Cientifico cientifico;

    // Creamos el constructor
    public Experimento(String nombre, Cientifico cientifico) {
        this.id = ++idValor;
        this.nombre = nombre;
        this.cientifico = cientifico;
    }

    // Creamos los get y set
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cientifico getCientifico() {
        return cientifico;
    }

    public void setCientifico(Cientifico cientifico) throws CientificoException {
        this.cientifico = cientifico;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experimento that = (Experimento) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Creamos un toString
    @Override
    public String toString() {
        return String.format("Iddentificador: %d, Nombre: %s, Científico: %s", this.id, this.nombre, this.cientifico);
    }
}