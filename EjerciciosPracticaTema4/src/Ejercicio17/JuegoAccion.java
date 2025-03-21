package Ejercicio17;

import java.util.ArrayList;
import java.util.List;

public class JuegoAccion extends Juego implements Actualizable {

    // Creamos los atributos
    private List<Integer> niveles;
    private List<String> personajes;
    private int numActualizaciones;
    public static final int COSTE_ACTUALIZACION = 5;

    // Creamos el constructor
    public JuegoAccion(String titulo, String estudio, double costoDesarrollo) throws JuegoException {
        super(titulo, estudio, costoDesarrollo);
        this.niveles = new ArrayList<>();
        this.personajes = new ArrayList<>();
        this.numActualizaciones = 0;
    }

    // Hacemos los get y set
    public List<Integer> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Integer> niveles) {
        this.niveles = niveles;
    }

    public List<String> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<String> personajes) {
        this.personajes = personajes;
    }

    public int getNumActualizaciones() {
        return numActualizaciones;
    }

    protected void setNumActualizaciones(int numActualizaciones) {
        this.numActualizaciones = numActualizaciones;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + ", Niveles: " + this.niveles + ", Personajes: " + this.personajes
                + "Número de actualizaciones: " + this.numActualizaciones;
    }

    @Override
    public double calcularCostoTotal() {
        return super.getCostoDesarrollo() + (numActualizaciones * COSTE_ACTUALIZACION);
    }

    @Override
    public void actualizar() {
        System.out.println("Actualizando el juego " + super.getTitulo() + "...");
        numActualizaciones++;
    }
}