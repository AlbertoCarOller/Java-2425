package Extra.Ejercicio16;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Proyecto {
    // Creamos los atributos
    private String nombre;
    private List<Tarea> tareas;
    private Set<Participante> participantes;

    // Creamos el constructor
    public Proyecto(String nombre) {
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
        this.participantes = new HashSet<>();
    }

    // Creamos los get
    public String getNombre() {
        return nombre;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public Set<Participante> getParticipantes() {
        return participantes;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proyecto proyecto = (Proyecto) o;
        return Objects.equals(nombre, proyecto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Tareas: %s, Participantes: %s", this.nombre, this.tareas, this.participantes);
    }

    // Hacemos un método para añadir un participante a un proyecto
    public void registrarParticipante(Participante participante) throws ProyectoException {
        if (!participantes.add(participante)) {
            throw new ProyectoException("El participante ya forma parte del proyecto");
        }
    }

    // Hacemos un método para añadir tareas de manera indefinida a al proyecto
    public void registrarTareas(Tarea... tareas) throws ProyectoException {
        if (this.tareas.stream().anyMatch(t -> {
            for (Tarea tarea : tareas) {
                if (t.equals(tarea)) {
                    return true;
                }
            }
            return false;
        })) {
            throw new ProyectoException("Alguna de esas tareas ya está registrada");
        }
        this.tareas.addAll(Arrays.asList(tareas));
    }

    // Hacemos un método para asignar una tarea a un participante
    public void anadirTareaAParticipante(Participante participante, Tarea tarea) throws ProyectoException {
        if (!participantes.contains(participante) || !tareas.contains(tarea)) {
            throw new ProyectoException("El participante o la tarea no forman parte del proyecto");
        }
        if (participante.getTareaAsignada() != null) {
            throw new ProyectoException("El participante ya tiene una tarea asignada");
        }
        if (tarea.getEstado() == Estado.COMPLETADA || tarea.getEstado() == Estado.EN_PROGRESO) {
            throw new ProyectoException("La tarea ya ha sido completada o está en proceso");
        }
        tarea.setEstado(Estado.EN_PROGRESO);
        participante.setTareaAsignada(tarea);
    }

    // Hacemos un método para completar una tarea de un participante
    public void completarTarea(Participante participante) throws ProyectoException {
        if (!participantes.contains(participante)) {
            throw new ProyectoException("El participante no está registrado en el proyecto");
        }
        if (participante.getTareaAsignada() == null) {
            throw new ProyectoException("No tiene tarea asignada este participante");
        }
        Tarea tarea = this.tareas.stream().filter(t -> t.equals(participante.getTareaAsignada())).findFirst()
                .orElseThrow(() -> new ProyectoException("La tarea no se encuentra registrada"));
        tarea.setEstado(Estado.COMPLETADA);
        tarea.setFechaFinalizacion(LocalDateTime.now());
        participante.setTareaAsignada(null);
        participante.setTareasCompletas(participante.getTareasCompletas() + 1);
    }

    // Hacemos un método para obtener una lista de tareas del proyecto ordenadas por estado
    public List<Tarea> tareasOrdenadasPorEstado() throws ProyectoException {
        if (tareas.isEmpty()) {
            throw new ProyectoException("No hay tareas");
        }
        return tareas.stream().sorted(Comparator.comparing(t -> t.getEstado().name()))
                .sorted((t1, t2) -> {
                    if (t1.getEstado() == t2.getEstado()) {
                        return t1.getTitulo().compareTo(t2.getTitulo());
                    }
                    return 0;
                }).toList();
    }

    /* Hacemos un método que devuelva una lista de participantes con sus tareas (dentro de los mismos) ordenado
     por el número de tareas completadas */
    public List<Participante> participantesOrdenados() throws ProyectoException {
        if (participantes.isEmpty()) {
            throw new ProyectoException("No hay participantes");
        }
        return participantes.stream().sorted(Comparator.comparingInt(Participante::getTareasCompletas)
                .thenComparing(Participante::getNombre)).toList();
    }

    // Hacemos un método que va a devolver una lista de tareas del proyecto sin completar que lleven más de n horas
    public List<Tarea> tareasMasHoras(int n) throws ProyectoException {
        if (tareas.isEmpty()) {
            throw new ProyectoException("No hay proyectos");
        }
        return this.tareas.stream().filter(t -> t.getEstado() != Estado.COMPLETADA
                && Duration.between(t.getFechaAsignacion(), LocalDateTime.now()).toHours() > n).toList();
    }

    /* Hacemos un método que va a eliminar un participante de un proyecto, si el participante tiene una tarea
     * asignada, esta se le pasará al participante que no tenga una tarea asignada, en caso de empate se
     * elegirá alfabéticamente */
    public void eliminarParticipante(Participante participante) throws ProyectoException {
        if (!participantes.contains(participante)) {
            throw new ProyectoException("El participante no se encuentra en el proyecto");
        }
        if (participante.getTareaAsignada() == null) {
            participantes.remove(participante);
        } else {
            Tarea tareaAsignada;
            tareaAsignada = participante.getTareaAsignada();
            Participante participanteAMeter = participantes.stream().filter(p -> p.getTareaAsignada() == null)
                    .min(Comparator.comparing(Participante::getNombre))
                    .orElse(participantes.stream().min(Comparator.comparing(Participante::getNombre))
                            .orElseThrow(() -> new ProyectoException("No se encuentran participantes")));
            if (participanteAMeter.getTareaAsignada() != null) {
                participanteAMeter.getTareaAsignada().setEstado(Estado.COMPLETADA);
                participanteAMeter.setTareaAsignada(null);
                participanteAMeter.setTareaAsignada(tareaAsignada);
                participantes.remove(participante);
            }
        }
    }
}