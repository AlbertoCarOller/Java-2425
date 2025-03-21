package Extra.Ejercicio12;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Evento {

    // Creamos los atributos
    private String nombre;
    private int minutos;
    private Set<Ponente> ponentes;

    // Creamos el constructor
    public Evento(String nombre, int minutos) throws EventoException {
        this.nombre = nombre;
        setMinutos(minutos);
        this.ponentes = new HashSet<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMinutos() {
        return minutos;
    }

    private void setMinutos(int minutos) throws EventoException {
        if (minutos < 1) {
            throw new EventoException("El evento no puede durar menos de 1 minuto");
        }
        this.minutos = minutos;
    }

    public Set<Ponente> getPonentes() {
        return ponentes;
    }

    private void setPonentes(Set<Ponente> ponentes) {
        this.ponentes = ponentes;
    }

    // Hacemos el equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(nombre, evento.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre; %s, Minutos: %d, Ponentes: %s", this.nombre, this.minutos, this.ponentes);
    }

    // Hacemos un método para registrar un ponente en el evento
    public void registrarPonente(Ponente ponente) throws EventoException {
        if (ponentes.contains(ponente)) {
            throw new EventoException("El evento ya está registrado en el evento");
        }
        ponentes.add(ponente);
    }
}