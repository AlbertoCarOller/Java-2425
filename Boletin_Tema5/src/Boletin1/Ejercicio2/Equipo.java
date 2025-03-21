package Boletin1.Ejercicio2;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Equipo<T> {

    // Creamos los métodos
    private String nombre;
    private Set<T> alumnos;

    // Generamos el constructor
    public Equipo(String nombre) {
        this.nombre = nombre;
        // Hacemos el 'LinkedHashSet<>' crea un conjusnto con lista de enlaces, lo cuál es más eficiente para añadir y eliminar
        this.alumnos = new LinkedHashSet<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<T> getAlumnos() {
        return alumnos;
    }

    private void setAlumnos(Set<T> alumnos) {
        this.alumnos = alumnos;
    }

    // Hacemos un método para añadir alumnos al conjunto y si ya existe no se añade
    public void addAlumno(T alumno) throws EquipoException {
        if (alumnos.contains(alumno)) {
            throw new EquipoException("El alumno ya existe");
        }
        alumnos.add(alumno);
    }

    // Hacemos un método para eliminar un alumno
    public void deleteAlumno(T alumno) throws EquipoException {
        if (!alumnos.contains(alumno)) {
            throw new EquipoException("El alumno no está en la lista");
        }
        alumnos.remove(alumno);
    }

    // Hacemos un método para buscar si un alumno pertenece al equipo
    public T comprobarAlumno(T alumno) {
        if (alumnos.contains(alumno)) {
            return alumno;
        }
        return null;
    }

    // Hacemos un método para mostrar el equipo
    public List<T> mostrarEquipo() {
        return new ArrayList<>(alumnos);
    }

    // Hacemos un método para unir dos equipos
    public Equipo<T> unirEquipos(Equipo<T> equipo) throws EquipoException {
        // Creamos un nuevo equipo
        Equipo<T> nuevoEquipo = new Equipo<>("Unión Equipos");
        // Se añade al nuevo equipo creado los alumnos de los dos equipos
        nuevoEquipo.getAlumnos().addAll(this.alumnos);
        nuevoEquipo.getAlumnos().addAll(equipo.getAlumnos());
        // Devolvemos el nuevo equipo
        return nuevoEquipo;
    }

    // Hacemos un método para hacer una insercción de equipos
    public Equipo<T> inserccionEquipo(Equipo<T> equipo) {
        Equipo<T> nuevoEquipo2 = new Equipo<>("Insercción Equipo");
        // Guardamos en el nuevo equipo los alumnos de un equipo
        nuevoEquipo2.getAlumnos().addAll(this.alumnos);
        // Retenemos en el nuevoEquipo2 los alumnos que coinciden en los dos, es decir la intersección
        nuevoEquipo2.getAlumnos().retainAll(equipo.getAlumnos());
        return nuevoEquipo2;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", alumnos=" + alumnos +
                '}';
    }
}