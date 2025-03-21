package Gimnasio;

import java.util.Arrays;
import java.util.Objects;

public class Entrenamiento {

    // Creamos los atributos
    private String nombre;
    private int numActividades;
    private Actividad[] actividades;

    // Creamos el constructor

    public Entrenamiento(String nombre, int numActividades) throws GimnasioException {
        this.nombre = nombre;
        setNumActividades(numActividades);
        this.actividades = new Actividad[numActividades];
    }

    // Creamos los get y los set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumActividades() {
        return numActividades;
    }

    private void setNumActividades(int numActividades) throws GimnasioException {
        // Si el número de actividades es menor que uno saltará una excepción
        if (numActividades < 1) {
            throw new GimnasioException("No puede haber menos de una actividad");
        }
        this.numActividades = numActividades;
    }

    public Actividad[] getActividades() {
        return actividades;
    }

    public void setActividades(Actividad[] actividades) {
        this.actividades = actividades;
    }

    // Creamos un toString
    @Override
    public String toString() {
        return "Entrenamiento{" +
                "nombre='" + nombre + '\'' +
                ", numActividades=" + numActividades +
                ", actividades=" + Arrays.toString(actividades) +
                '}';
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrenamiento that = (Entrenamiento) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}