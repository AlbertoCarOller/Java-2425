package Extra.Ejercicio6;

import Boletin1.Ejercicio3.PersonaExcepcion;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class AtraccionApp {
    static Deque<Persona> filaPersonas = new ArrayDeque<>();
    static Atraccion atraccion = new Atraccion("Chelu´s atracción");

    public static void main(String[] args) {
        try {
            // Creamos distintas personas
            Persona persona = new Persona("Chelu", 13, LocalTime.of(12, 30));
            Persona persona1 = new Persona("Atisbedo", 45, LocalTime.of(10, 45));
            Persona persona2 = new Persona("Chicote", 87, LocalTime.of(17, 10));
            Persona persona3 = new Persona("Carles Xavier", 17, LocalTime.of(15, 28));
            Persona persona4 = new Persona("Saragarcon", 23, LocalTime.of(19, 30));
            Persona persona5 = new Persona("Bermudín", 35, LocalTime.of(19, 20));
            // Añadimos a la fila las personas
            anadirPersonaFila(persona);
            anadirPersonaFila(persona1);
            anadirPersonaFila(persona2);
            anadirPersonaFila(persona3);
            anadirPersonaFila(persona4);
            anadirPersonaFila(persona5);
            // Metemos a las personas a la atracción
            pasarAtraccion(3);
            pasarAtraccion(1);
            // Mostramos el número de personas que hay en la fila
            System.out.printf("\nNúmero de personas: %d", mostrarCantidad());
            // Mostramos las personas de la fila
            System.out.println();
            mostrarPersonasEnFila();
            // Mostramos las personas dentro de la atracción ordenada por edad
            System.out.println();
            System.out.println(mostrarPersonasPorEdad());
            // Mostramos las personas dentro de la atracción
            System.out.println();
            System.out.println(mostrarPersonasAlfabeticamente());

        } catch (PersonaExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método para añadir personas a la fila
    public static void anadirPersonaFila(Persona persona) throws PersonaExcepcion {
        for (Persona personaRecorrida : filaPersonas) {
            if (persona.equals(personaRecorrida)) {
                throw new PersonaExcepcion(persona.getNombre() + " ya está en la fila");
            }
        }
        filaPersonas.add(persona);
    }

    // Hacemos un método para eliminar y meter en la atracción a un número determinado de personas
    public static void pasarAtraccion(int numPersonas) throws PersonaExcepcion {
        if (numPersonas > 5 || numPersonas > filaPersonas.size()) {
            throw new PersonaExcepcion("No se pueden añadir tantas personas");
        }
        if (numPersonas == 0) {
            throw new PersonaExcepcion("No puedes no añadir a ninguna persona");
        }
        while (numPersonas != 0) {
            Persona persona = filaPersonas.poll();
            if (persona != null) {
                atraccion.anadirPersona(persona);
                System.out.println("Se ha añadido a la " + atraccion.getNombre() + " a " + persona.getNombre());
                numPersonas--;
            }
        }
    }

    // Hacemos un método para comrpobar cuántas personas están esperando la fila
    public static long mostrarCantidad() {
        return filaPersonas.stream().count();
    }

    // Hacemos un método para mostrar las personas que están esperando la fila
    public static void mostrarPersonasEnFila() {
        filaPersonas.forEach(System.out::println);
    }

    // Hacemos un método para mostrar las personas de la atracción ordenadas por edad
    public static List<Persona> mostrarPersonasPorEdad() {
        return atraccion.getPersonas().stream().sorted(Comparator.comparingInt(Persona::getEdad)).toList();
    }

    // Hacemos un método para mostrar las personas que sus nombres empiecen por C ordenado alfabéticamente que están en la atracción
    public static List<Persona> mostrarPersonasAlfabeticamente() {
        return atraccion.getPersonas().stream().filter(p -> Character.toUpperCase(p.getNombre().charAt(0)) == 'C')
                .sorted(Comparator.comparing(Persona::getNombre)).toList();
    }
}