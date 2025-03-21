package Extra.Ejercicio10;

import java.util.*;
import java.util.stream.Collectors;

public class Grupo {

    // Creamos los atributos
    private String nombre;
    private List<String> miembros;
    private Queue<String> eventos;
    private List<String> eventosCompletados;

    // Creamos el constructor
    public Grupo(String nombre) throws GrupoException {
        setNombre(nombre);
        this.miembros = new ArrayList<>();
        this.eventos = new ArrayDeque<>();
        this.eventosCompletados = new ArrayList<>();
    }

    // Hacemos los get
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws GrupoException {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new GrupoException("El nombre del grupo debe de empezar por mayúsculas");
        }
        this.nombre = nombre;
    }

    public List<String> getMiembros() {
        return miembros;
    }

    public Queue<String> getEventos() {
        return eventos;
    }

    public List<String> getEventosCompletados() {
        return eventosCompletados;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return Objects.equals(nombre, grupo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Miembros: %s, Eventos: %s", this.nombre, this.miembros, this.eventos);
    }

    // Hacemos un método para añadir una persona al grupo
    public void registrarMiembro(String miembro) throws GrupoException {
        comprobarMayuscula(miembro);
        if (miembros.contains(miembro)) {
            throw new GrupoException("El miembro ya está en el grupo");
        }
        miembros.add(miembro);
    }

    // Hacemos un método para eliminar una persona del grupo
    public void eliminarMiembro(String miembro) throws GrupoException {
        comprobarMayuscula(miembro);
        int indice = miembros.indexOf(miembro);
        if (indice == -1) {
            throw new GrupoException("No se ha encontrado el miembro");
        }
        miembros.remove(indice);
    }

    // Hacemos un método para añadir un evento a una cola
    public void registrarEvento(String evento) throws GrupoException {
        comprobarMayuscula(evento);
        if (eventos.contains(evento)) {
            throw new GrupoException("El evento ya existe");
        }
        if (eventosCompletados.contains(evento)) {
            throw new GrupoException("El evento ya se completó");
        }
        eventos.add(evento);
    }

    // Hacemos un método para eliminar un evento de una cola
    public void eliminarEvento() throws GrupoException {
        if (eventos.isEmpty()) {
            throw new GrupoException("No hay eventos en la cola");
        }
        eventosCompletados.add(eventos.poll());
    }

    // Hacemos un método para comprobar que la primera letra de una palabra esté en mayúsculas y el resto en minúsculas
    private void comprobarMayuscula(String texto) throws GrupoException {
        if (!Character.isUpperCase(texto.charAt(0))) {
            throw new GrupoException("La primera letra debe estar en mayúsculas");
        }
        for (int i = 1; i < texto.length(); i++) {
            if (Character.isUpperCase(texto.charAt(i))) {
                throw new GrupoException("El resto de letras deben estar en minúsculas");
            }
        }
    }

    // Hacemos un método para mostrar las actividades
    public String mostrarEventos() throws GrupoException {
        if (eventos.isEmpty()) {
            throw new GrupoException("No hay eventos");
        }
        return eventos.stream().sorted(String::compareTo)
                .reduce((e1, e2) -> e1 + ", " + e2).orElseThrow(() -> new GrupoException("No hay eventos"));
    }

    // Hacemos un método para mostrar los miembros
    public String mostrarMiembros() throws GrupoException {
        if (miembros.isEmpty()) {
            throw new GrupoException("No hay miembros");
        }
        return miembros.stream().sorted(String::compareTo)
                .reduce((m1, m2) -> m1 + ", " + m2).orElseThrow(() -> new GrupoException("No hay miembros"));
    }

    // Hacemos un método para buscar si hay una actividad concreta programada para el grupo
    public boolean buscarEvento(String evento) {
        return eventos.stream().anyMatch(evento::equals);
    }

    // Hacemos un método que va a devolver un String con todos los eventos ordenados alfabéticamente
    public String ordenarEventos() {
        return eventos.stream().sorted(String::compareTo).collect(Collectors.joining(", "));
    }
}