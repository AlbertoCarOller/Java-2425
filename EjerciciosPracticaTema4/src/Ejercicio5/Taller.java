package Ejercicio5;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Taller extends Evento implements Bebible {

    // Hacemos los atributos
    private String[] herramientas;
    private static final int MAX_HERRAMIENTAS = 10;
    private static final int PRECIO_BEBIDA = 7;

    // Hacemos el constructor
    public Taller(String nombre, LocalDateTime fecha, String ubicacion, double precioEntrada) throws EventoException {
        super(nombre, fecha, ubicacion, precioEntrada);
        this.herramientas = new String[MAX_HERRAMIENTAS];
    }

    // Hacemos los get y set
    public String[] getHerramientas() {
        return herramientas;
    }

    private void setHerramientas(String[] herramientas) {
        this.herramientas = herramientas;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + ", herramientas=" + Arrays.toString(this.herramientas) + '}';
    }

    @Override
    public int calcularPrecioBebible() {
        return (int) (super.getCantidadPersonas() * super.getPrecioEntrada()) + (super.getCantidadPersonas() * PRECIO_BEBIDA);
    }

    // Hacemos un método para añadir una herramienta a la lista
    public void anadirHerramienta(String nombreHerramienta) throws EventoException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < herramientas.length && !esIgual; i++) {
            if (nombreHerramienta.equalsIgnoreCase(herramientas[i])) {
                esIgual = true;
            }
            if (herramientas[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            herramientas[hayEspacio] = nombreHerramienta;

        } else {
            throw new EventoException("No se puede añadir la herramienta");
        }
    }

    // Hacemos un método para eliminar una herramienta
    public void eliminarHerramienta(String nombreHerramienta) throws EventoException {
        boolean existe = false;
        for (int i = 0; i < herramientas.length; i++) {
            if (nombreHerramienta.equalsIgnoreCase(herramientas[i])) {
                herramientas[i] = null;
                existe = true;
            }
        }
        if (!existe) {
            throw new EventoException("No se ha encontrado la herramienta");
        }
    }
}