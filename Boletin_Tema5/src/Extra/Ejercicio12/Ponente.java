package Extra.Ejercicio12;

import java.util.Objects;

public class Ponente {

    // Creamos los atributos
    private String nombre;
    private String pais;
    private Especialidad especialidad;

    // Hacemos el constructor
    public Ponente(String nombre, String pais, Especialidad especialidad) {
        this.nombre = nombre;
        this.pais = pais;
        this.especialidad = especialidad;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    private void setPais(String pais) {
        this.pais = pais;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    private void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ponente ponente = (Ponente) o;
        return Objects.equals(nombre, ponente.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, País: %s, Especialidad: %s", this.nombre, this.pais, this.especialidad);
    }
}