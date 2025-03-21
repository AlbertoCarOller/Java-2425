package Extra.Ejercicio11;

import Extra.Ejercicio10.GrupoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Taller {

    // Creamos los atributos
    private String titulo;
    private LocalDate fechaCelebracion;
    private List<Participante> participantes;
    private static final int MAX_PARTICIPANTES = 6;

    // Creamos el constructor
    public Taller(String titulo, LocalDate fechaCelebracion) {
        this.titulo = titulo;
        this.fechaCelebracion = fechaCelebracion;
        this.participantes = new ArrayList<>(MAX_PARTICIPANTES);
    }

    // Hacemos los get y set
    public String getTitulo() {
        return titulo;
    }

    private void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaCelebracion() {
        return fechaCelebracion;
    }

    private void setFechaCelebracion(LocalDate fechaCelebracion) throws ParticipanteException {
        if (fechaCelebracion.isAfter(LocalDate.now())) {
            throw new ParticipanteException("La fecha de celebración no puede ser mayor a la actual");
        }
        this.fechaCelebracion = fechaCelebracion;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    private void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taller taller = (Taller) o;
        return Objects.equals(titulo, taller.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Título: %s, Fecha celebración: %s, Participantes: %s", this.titulo,
                this.fechaCelebracion.format(DateTimeFormatter.ofPattern("yy/MM/dd")), this.participantes);
    }

    // Hacemos un método para añadir un nuevo participante en el taller
    public void registrarParticipante(Participante participante) throws ParticipanteException {
        if (participantes.contains(participante)) {
            throw new ParticipanteException("El participante ya está registrado");
        }
        if (participantes.size() == MAX_PARTICIPANTES) {
            throw new ParticipanteException("No se pueden añadir más participantes");
        }
        participantes.add(participante);
    }

    // Hacemos un método para eliminar un participante del taller
    public void eliminarParticipante(Participante participante) throws ParticipanteException {
        if (!participantes.contains(participante)) {
            throw new ParticipanteException("El participante no está registrado");
        }
        participantes.remove(participante);
    }

    // Hacemos un método para buscar participante mediante su email
    public Participante buscarParticipante(String email) {
        return participantes.stream().filter(p -> p.getEmail().equalsIgnoreCase(email))
                .findFirst().orElse(null);
    }

    // Hacemos un método que va a devolver un String de los participantes ordenado alfabéticamente
    public String participantesOrdenadosAlfabeticamente() throws ParticipanteException {
        if (participantes.isEmpty()) {
            throw new ParticipanteException("No hay participantes");
        }
        return participantes.stream().sorted(Comparator.comparing(Participante::getNombre))
                .map(Participante::getNombre).collect(Collectors.joining(", "));
    }

    /* Hacemos un método que va a devolver un String de participantes que empiecen por una letra,
     ordenado por el email */
    public String buscarParticipantesPorLetra(char letra) throws ParticipanteException {
        String participantesString = participantes.stream()
                .sorted(Comparator.comparing(Participante::getEmail))
                .map(Participante::getNombre)
                .filter(nombre -> nombre.charAt(0) == letra)
                .collect(Collectors.joining(", "));
        if (participantesString.isEmpty()) {
            throw new ParticipanteException("No se ha encontrado ningún participante que empiece por " + letra);
        }
        return participantesString;
    }

    /* Hacemos un método para obtener un String de los teléfonos de aquellos participantes que su email
     pertenezca a un dominio en concreto */
    public String telefonosPorDominio(String dominio) throws ParticipanteException {
        String numTelef = participantes.stream().filter(p -> p.getEmail().endsWith(dominio))
                .map(Participante::getNumTelefono)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        if (numTelef.isEmpty()) {
            throw new ParticipanteException("No se ha encontrado ningún telefono que cumpla");
        }
        return numTelef;
    }
}