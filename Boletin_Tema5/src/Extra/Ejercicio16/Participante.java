package Extra.Ejercicio16;

import java.util.Objects;

public class Participante {
    // Creamos los atributos
    private String nombre;
    private int id;
    private static int valorId;
    private Tarea tareaAsignada;
    private int tareasCompletas;

    // Creamos el constructor
    public Participante(String nombre) {
        this.nombre = nombre;
        this.id = ++valorId;
        this.tareasCompletas = 0;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public Tarea getTareaAsignada() {
        return tareaAsignada;
    }

    public void setTareaAsignada(Tarea tareaAsignada) {
        this.tareaAsignada = tareaAsignada;
    }

    public int getTareasCompletas() {
        return tareasCompletas;
    }

    public void setTareasCompletas(int tareasCompletas) {
        this.tareasCompletas = tareasCompletas;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, ID: %d, Tarea asignada: %s, Tareas completadas: %d", this.nombre,
                this.id, this.tareaAsignada, this.tareasCompletas);
    }
}