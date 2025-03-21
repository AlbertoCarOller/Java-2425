package Boletin1.Ejercicio1;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Equipo {

    // Creamos los métodos
    private String nombre;
    private Set<Alumno> alumnos;

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

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    private void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    // Hacemos un método para añadir alumnos al conjunto y si ya existe no se añade
    public void addAlumno(Alumno alumno) throws EquipoExcepcion {
        if (alumnos.contains(alumno)) {
            throw new EquipoExcepcion("El alumno ya existe");
        }
        alumnos.add(alumno);
    }

    // Hacemos un método para eliminar un alumno
    public void deleteAlumno(Alumno alumno) throws EquipoExcepcion {
        if (!alumnos.contains(alumno)) {
            throw new EquipoExcepcion("El alumno no está en la lista");
        }
        alumnos.remove(alumno);
    }

    // Hacemos un método para buscar si un alumno pertenece al equipo
    public Alumno comprobarAlumno(Alumno alumno) {
        if (alumnos.contains(alumno)) {
            return alumno;
        }
        return null;
    }

    // Hacemos un método para mostrar el equipo
    public List<Alumno> mostrarEquipo() {
        /* Devolvemos una lista que acabamos de crear hecha a partir de un Set, ya que el constructor de las listas
         permiten esto, devolver una colección */
        return new ArrayList<>(alumnos);
    }

    // Hacemos un método para unir dos equipos
    public Equipo unirEquipos(Equipo equipo) throws EquipoExcepcion {
        // Creamos un nuevo equipo
        Equipo nuevoEquipo = new Equipo("Unión Equipos");
        // Se añade al nuevo equipo creado los alumnos de los dos equipos
        nuevoEquipo.getAlumnos().addAll(this.alumnos);
        nuevoEquipo.getAlumnos().addAll(equipo.getAlumnos());
        // Devolvemos el nuevo equipo
        return nuevoEquipo;
    }

    // Hacemos un método para hacer una insercción de equipos
    public Equipo inserccionEquipo(Equipo equipo) {
        // Creamos el nuevo equipo
        Equipo nuevoEquipo2 = new Equipo("Insercción Equipo");
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