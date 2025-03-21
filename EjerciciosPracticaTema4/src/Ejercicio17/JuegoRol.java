package Ejercicio17;

import java.util.ArrayList;
import java.util.List;

public class JuegoRol extends Juego implements Actualizable {

    // Creamos los atributos
    private Complejidad complejidad;
    private int numActualizaciones;
    public static final int COSTO_ACTUALIZACION = 10;
    private List<String> personalizaciones;

    // Creamos el constructor
    public JuegoRol(String titulo, String estudio, double costoDesarrollo, Complejidad complejidad) throws JuegoException {
        super(titulo, estudio, costoDesarrollo);
        this.complejidad = complejidad;
        this.personalizaciones = new ArrayList<>();
    }

    // Hacemos los get y set
    public Complejidad getComplejidad() {
        return complejidad;
    }

    private void setComplejidad(Complejidad complejidad) {
        this.complejidad = complejidad;
    }

    public int getNumActualizaciones() {
        return numActualizaciones;
    }

    protected void setNumActualizaciones(int numActualizaciones) {
        this.numActualizaciones = numActualizaciones;
    }

    public List<String> getPersonalizaciones() {
        return personalizaciones;
    }

    private void setPersonalizaciones(List<String> personalizaciones) {
        this.personalizaciones = personalizaciones;
    }

    @Override
    public double calcularCostoTotal() {
        return super.getCostoDesarrollo() + (numActualizaciones * COSTO_ACTUALIZACION);
    }

    @Override
    public void actualizar() {
        System.out.println("Actualizando el juego " + super.getTitulo() + "...");
        numActualizaciones++;
    }

    // Modificamos el toString
    @Override
    public String toString() {
        return super.toString() + ", Complejidad: " + this.complejidad + ", Número actualizaciones :"
                + this.numActualizaciones + ", Personalizaciones: " + this.personalizaciones;
    }
}