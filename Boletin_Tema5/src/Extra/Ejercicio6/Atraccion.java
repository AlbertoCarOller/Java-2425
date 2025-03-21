package Extra.Ejercicio6;

import Boletin1.Ejercicio3.PersonaExcepcion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Atraccion {

    // Creamos los atributos
    private String nombre;
    private List<Persona> personas;
    private int contadorPersonas;
    private static final int MAX_PERSONAS = 5;

    // Creamos el constructor
    public Atraccion(String nombre) {
        this.nombre = nombre;
        this.personas = new ArrayList<>(MAX_PERSONAS);
        this.contadorPersonas = 0;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    private void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public int getContadorPersonas() {
        return contadorPersonas;
    }

    private void setContadorPersonas(int contadorPersonas) {
        this.contadorPersonas = contadorPersonas;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atraccion atraccion = (Atraccion) o;
        return Objects.equals(nombre, atraccion.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %S, Personas: %s, Personas dentro de la atracción: %d", this.nombre,
                this.personas, this.contadorPersonas);
    }

    // Hacemos un método para añadir
    public void anadirPersona(Persona persona) throws PersonaExcepcion {
        if (contadorPersonas == MAX_PERSONAS) {
            throw new PersonaExcepcion("No se pueden añadir más personas");
        }
        personas.add(persona);
        this.contadorPersonas++;
    }

    // Hacemos un método para eliminar todas las personas de la atracción
    public void eliminarPersonas() throws PersonaExcepcion {
        if (personas.isEmpty()) {
            throw new PersonaExcepcion("No puedes eliminar a nadie de la atracción, ya que no hay nadie");
        }
        personas.clear();
        this.contadorPersonas = 0;
    }
}