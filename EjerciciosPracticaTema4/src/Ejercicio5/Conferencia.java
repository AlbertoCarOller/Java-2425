package Ejercicio5;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Conferencia extends Evento implements Comible {

    // Hacemos los atributos
    private String[] conferenciantes;
    private static final int MAX_CONFERENCIANTES = 5;
    private static final int PRECIO_CON_COMIDA = 15;

    // Creamos el constructor
    public Conferencia(String nombre, LocalDateTime fecha, String ubicacion, double precioEntrada) throws EventoException {
        super(nombre, fecha, ubicacion, precioEntrada);
        this.conferenciantes = new String[MAX_CONFERENCIANTES];
    }

    // Hacemos los get y set
    public String[] getConferenciantes() {
        return conferenciantes;
    }

    private void setConferenciantes(String[] conferenciantes) {
        this.conferenciantes = conferenciantes;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + ", conferenciantes=" + Arrays.toString(this.conferenciantes) + '}';
    }

    // Implementamos el método para calcular lo recaudado por un evento comible
    @Override
    public int calcularPrecioComible() {
        return (int) (super.getCantidadPersonas() * super.getPrecioEntrada()) + (super.getCantidadPersonas() * PRECIO_CON_COMIDA);
    }

    // Hacemos un método para añadir un conferenciante
    public void anadirConferenciante(String conferenciante) throws EventoException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < conferenciantes.length && !esIgual; i++) {
            if (conferenciante.equalsIgnoreCase(conferenciantes[i])) {
                esIgual = true;
            }
            if (conferenciantes[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            conferenciantes[hayEspacio] = conferenciante;

        } else {
            throw new EventoException("No se puede registrar el conferenciante");
        }
    }

    // Hacemos un método para eliminar un conferenciante
    public void eliminarConferenciante(String nombreConferenciante) throws EventoException {
        boolean encontrado = false;
        for (int i = 0; i < conferenciantes.length; i++) {
            if (nombreConferenciante.equalsIgnoreCase(conferenciantes[i])) {
                conferenciantes[i] = null;
                encontrado = true;
            }
        }
        if (!encontrado) {
            throw new EventoException("No se ha encontrado el conferenciante");
        }
    }
}