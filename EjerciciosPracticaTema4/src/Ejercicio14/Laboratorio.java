package Ejercicio14;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class Laboratorio {

    // Creamos los atributos
    private String nombre;
    private List<Experimento> experimentos;

    // Creamos el constructor
    public Laboratorio(String nombre) {
        this.nombre = nombre;
        this.experimentos = new ArrayList<>();
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Experimento> getExperimentos() {
        return experimentos;
    }

    private void setExperimentos(List<Experimento> experimentos) {
        this.experimentos = experimentos;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laboratorio that = (Laboratorio) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Experimentos: %s", this.nombre, this.experimentos);
    }

    // Hacemos un método para añadir un experimento a un laboratorio
    public void anadirExperimento(Experimento experimento) throws CientificoException {
        if (!(experimento instanceof ExperimentoConfidencial && this instanceof LaboratorioAltoSecreto) &&
                !(experimento instanceof ExperimentoReproducido && this instanceof LaboratorioReproducido)) {
            throw new CientificoException("No se puede añadir el experimento ya que el laboratorio no está especializado en él");
        }
        Iterator<Experimento> it = experimentos.iterator();
        boolean esIgual = false;
        while (it.hasNext() && !esIgual) {
            Experimento experimentoIt = it.next();
            if (experimento.equals(experimentoIt)) {
                esIgual = true;
            }
        }
        if (esIgual) {
            throw new CientificoException("El experimento ya existe dentro del laboratorio");
        }
        experimentos.add(experimento);
    }
}