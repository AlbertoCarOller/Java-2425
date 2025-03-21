package Extra.Ejercicio4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Hallazgo implements Comparable<Hallazgo> {

    // Creamos los atributos
    private String nombre;
    private String descripcion;
    private LocalDate fechaEstimadaOrigen;
    private Map<String, List<Observacion>> observaciones;

    // Creamos el constructor
    public Hallazgo(String nombre, String descripcion, LocalDate fechaEstimadaOrigen) throws HallazgoException {
        this.nombre = nombre;
        this.descripcion = descripcion;
        setFechaEstimadaOrigen(fechaEstimadaOrigen);
        this.observaciones = new HashMap<>();
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaEstimadaOrigen() {
        return fechaEstimadaOrigen;
    }

    private void setFechaEstimadaOrigen(LocalDate fechaEstimadaOrigen) throws HallazgoException {
        if (fechaEstimadaOrigen.isAfter(LocalDate.now())) {
            throw new HallazgoException("El hallazgo no se ha podido descubrir más tarde de la fecha actual");
        }
        this.fechaEstimadaOrigen = fechaEstimadaOrigen;
    }

    public Map<String, List<Observacion>> getObservaciones() {
        return observaciones;
    }

    private void setObservaciones(Map<String, List<Observacion>> observaciones) {
        this.observaciones = observaciones;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hallazgo hallazgo = (Hallazgo) o;
        return Objects.equals(nombre, hallazgo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Descripción: %s, Fecha estimada del origen: %s, Observaciones: %s", this.nombre,
                this.descripcion, this.fechaEstimadaOrigen.format(DateTimeFormatter.ofPattern("yy/MM/dd")), this.observaciones);
    }

    // Hacemos un método que va a permitir registrar arqueólogos
    public void registrarArqueologo(String nombreArqueologo) throws HallazgoException {
        boolean esIgual = false;
        for (String arqueologo : observaciones.keySet()) {
            if (nombreArqueologo.equalsIgnoreCase(arqueologo)) {
                esIgual = true;
                break;
            }
        }
        if (esIgual) {
            throw new HallazgoException("No se puede registrar un arqueólogo que ya existe");
        }
        observaciones.put(nombreArqueologo, new ArrayList<>());
    }

    // Hacemos un método que nos va a permitir registrar observaciones de un arqueólogo
    public void registrarObservacion(String nombreArqueologo, Observacion observacion) throws HallazgoException {
        if (observaciones.isEmpty()) {
            throw new HallazgoException("No hay arqueólogos para registrar observaciones");
        }
        if (!nombreArqueologo.equalsIgnoreCase(observacion.getNombreArqueologo())) {
            throw new HallazgoException("El nombre del arquéologo que has pasado no coincide con el de la observación");
        }
        boolean encontrado = false;
        for (String arqueologo : observaciones.keySet()) {
            if (nombreArqueologo.equalsIgnoreCase(arqueologo)) {
                encontrado = true;
                observaciones.get(arqueologo).add(observacion);
                break;
            }
        }
        if (!encontrado) {
            throw new HallazgoException("No se ha encontrado el arqueólogo");
        }
    }

    // Hacemos un método para mostrar las observaciones con sus respectivos arqueólogos ordenados por la fecha
    public List<Observacion> mostrarObservacionesPorFecha() throws HallazgoException {
        if (observaciones.isEmpty()) {
            throw new HallazgoException("No hay observaciones");
        }
        List<Observacion> observacionesOrdenadas = new ArrayList<>();
        for (String arqueologo : observaciones.keySet()) {
            if (!observaciones.get(arqueologo).isEmpty()) {
                observacionesOrdenadas.addAll(observaciones.get(arqueologo));
            }
        }
        if (observacionesOrdenadas.isEmpty()) {
            throw new HallazgoException("No se ha podido ordenar nada, ya que no había observaciones bajo ningún arqueólogo");
        }
        observacionesOrdenadas.sort(null);
        return observacionesOrdenadas;
    }

    @Override
    public int compareTo(Hallazgo o) {
        return this.fechaEstimadaOrigen.compareTo(o.fechaEstimadaOrigen);
    }
}