package Extra.Ejercicio21;

import java.util.Objects;

public class Usuario {

    // Creamos los atributos
    private String nombre;
    private int id;
    private static int valorId = 0;

    // Creamos el constructor
    public Usuario(String nombre) {
        this.nombre = nombre;
        this.id = ++valorId;
    }

    // Creamos los get
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Creamos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, ID: %d", this.nombre, this.id);
    }
}