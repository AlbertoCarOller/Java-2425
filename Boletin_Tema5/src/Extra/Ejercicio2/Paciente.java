package Extra.Ejercicio2;

import java.util.Objects;

public class Paciente implements Comparable<Paciente> {

    // Creamos los atributos
    private String dni;
    private String nombre;
    private int edad;
    private int prioridad;

    // Creamos el constructor
    public Paciente(String dni, String nombre, int edad, int prioridad) throws PacienteException {
        setDni(dni);
        this.nombre = nombre;
        this.edad = edad;
        setPrioridad(prioridad);
    }

    // Hacemos los get y set
    public String getDni() {
        return dni;
    }

    private void setDni(String dni) throws PacienteException {
        if (dni.length() != 9 || !Character.isLetter(dni.charAt(8))) {
            throw new PacienteException("No está bien formado el DNI");
        }
        for (int i = 0; i < dni.length() - 1; i++) {
            if (!Character.isDigit(dni.charAt(i))) {
                throw new PacienteException("No está bien formado el DNI");
            }
        }
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    private void setPrioridad(int prioridad) throws PacienteException {
        if (prioridad < 1 || prioridad > 5) {
            throw new PacienteException("La prioridad no puede ser menor a 1 ni mayor a 5");
        }
        this.prioridad = prioridad;
    }

    public int getEdad() {
        return edad;
    }

    private void setEdad(int edad) throws PacienteException {
        if (edad < 0 || edad > 101) {
            throw new PacienteException("La edad no puede ser menor a 0 ni mayor a 101");
        }
        this.edad = edad;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(dni, paciente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("DNI: %s, Nombre: %s, Edad: %d, Prioridad: %d", this.dni, this.nombre, this.edad, this.prioridad);
    }

    // Implementamos el compareTo para poder ordenar la cola
    @Override
    public int compareTo(Paciente o) {
        return Integer.compare(this.prioridad, o.prioridad);
    }
}