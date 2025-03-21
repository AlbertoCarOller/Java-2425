package Extra.Ejercicio2;

import java.util.*;

public class Hospital {

    // Creamos los atributos
    private String nombre;
    private Queue<Paciente> pacientes;

    // Creamos el constructor
    public Hospital(String nombre) {
        this.nombre = nombre;
        this.pacientes = new PriorityQueue<>();
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Queue<Paciente> getPacientes() {
        return pacientes;
    }

    private void setPacientes(Queue<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return Objects.equals(nombre, hospital.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Pacientes: %s", this.nombre, this.pacientes);
    }

    // Hacemos un método para añadir un nuevo paciente a la cola
    public void anadirPaciente(Paciente paciente) throws PacienteException {
        boolean esIgual = false;
        Iterator<Paciente> it = pacientes.iterator();
        while (it.hasNext()) {
            if (paciente.equals(it.next())) {
                esIgual = true;
                break;
            }
        }
        if (esIgual) {
            throw new PacienteException("El paciente ya está en la cola");
        }
        pacientes.add(paciente);
    }

    // Hacemos un método que devolverá una lista con los pacientes que se atenderán por orden de prioridad
    public List<Paciente> ordenarPacientes() throws PacienteException {
        if (pacientes.isEmpty()) {
            throw new PacienteException("No hay pacientes");
        }
        List<Paciente> listaPacientesPrioridad = new ArrayList<>(this.pacientes);
        listaPacientesPrioridad.sort(null);
        return listaPacientesPrioridad;
    }

    // Hacemos un método para atender a un paciente de la lista
    public void atenderPaciente() throws PacienteException {
        if (pacientes.isEmpty()) {
            throw new PacienteException("No hay pacientes");
        }
        System.out.println("Ha sido atendido: " + pacientes.poll());
    }

    // Hacemos un método para mostrar los datos estadísticos
    public void mostrarDatosEstadisticos() throws PacienteException {
        int numPrioridad1 = 0;
        int numPrioridad2 = 0;
        int numPrioridad3 = 0;
        int numPrioridad4 = 0;
        int numPrioridad5 = 0;
        if (pacientes.isEmpty()) {
            throw new PacienteException("No hay pacientes");
        }
        Iterator<Paciente> it = pacientes.iterator();
        while (it.hasNext()) {
            Paciente paciente = it.next();
            if (paciente.getPrioridad() == 1) {
                numPrioridad1++;
            }
            if (paciente.getPrioridad() == 2) {
                numPrioridad2++;
            }
            if (paciente.getPrioridad() == 3) {
                numPrioridad3++;
            }
            if (paciente.getPrioridad() == 4) {
                numPrioridad4++;
            }
            if (paciente.getPrioridad() == 5) {
                numPrioridad5++;
            }
        }
        System.out.println("Prioridad 1: " + numPrioridad1);
        System.out.println("Prioridad 2: " + numPrioridad2);
        System.out.println("Prioridad 3: " + numPrioridad3);
        System.out.println("Prioridad 4: " + numPrioridad4);
        System.out.println("Prioridad 5: " + numPrioridad5);
    }

    // Hacemos un método que nos va a devolver una lista ordenado por edad
    public List<Paciente> ordenarPorEdad() throws PacienteException {
        List<Paciente> listaPacienteEdad = new ArrayList<>(pacientes);
        listaPacienteEdad.sort(new ComparadorPorEdad());
        return listaPacienteEdad;
    }
}