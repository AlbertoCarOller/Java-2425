package Extra.Ejercicio12;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Asistente {

    // Creamos los atributos
    private String nombre;
    private int id;
    private List<Evento> eventos;
    private static int valorId = 0;

    // Creamos el constructor
    public Asistente(String nombre) {
        this.nombre = nombre;
        this.id = ++valorId;
        this.eventos = new ArrayList<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    private void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asistente asistente = (Asistente) o;
        return id == asistente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Id: %d, Eventos: %s", this.nombre, this.id, this.eventos);
    }

    // Hacemos un método para registrar eventos
    public void registrarseEvento(Evento evento) throws EventoException {
        if (eventos.contains(evento)) {
            throw new EventoException("El evento ya existe");
        }
        eventos.add(evento);
    }
}