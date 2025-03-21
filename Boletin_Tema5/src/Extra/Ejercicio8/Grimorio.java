package Extra.Ejercicio8;

import java.util.Objects;

public class Grimorio {

    // Creamos los atributos
    private String nombre;
    private boolean peligroso;

    // Creamos el constructor
    public Grimorio(String nombre, boolean peligroso) throws GrimorioException {
        setNombre(nombre);
        this.peligroso = peligroso;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws GrimorioException {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new GrimorioException("El nombre del grimorio debe de empezar por mayúsculas");
        }
        this.nombre = nombre;
    }

    public boolean isPeligroso() {
        return peligroso;
    }

    protected void setPeligroso(boolean peligroso) {
        this.peligroso = peligroso;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grimorio grimorio = (Grimorio) o;
        return Objects.equals(nombre, grimorio.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Peligroso: %b", this.nombre, this.peligroso);
    }
}