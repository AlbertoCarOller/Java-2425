package Ejercicio10;

import java.util.Arrays;
import java.util.Objects;

public abstract class Agricultor implements Plantador, Cosechador, Regador {

    // Creamos los atributos
    private String nombre;
    private String[] equipo;
    private static final int HERRAMIENTAS_MAX = 10;

    // Creamos el constructor
    public Agricultor(String nombre) {
        this.nombre = nombre;
        this.equipo = new String[HERRAMIENTAS_MAX];
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String[] getEquipo() {
        return equipo;
    }

    private void setEquipo(String[] equipo) {
        this.equipo = equipo;
    }

    // Creamos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agricultor that = (Agricultor) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Creamos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Equipos: %s", this.nombre, Arrays.toString(this.equipo));
    }

    // Hacemos un método para contar la cantidad de herramientas del equipo tiene un agricultor
    public int numHerramientas() {
        int numHerramientas = 0;
        for (String herramienta : this.equipo) {
            if (herramienta != null) {
                numHerramientas++;
            }
        }
        return numHerramientas;
    }

    // Hacemos un método para añadir una herramienta
    public void anadirHerramienta(String nombreHerramienta) throws CultivoException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < equipo.length && !esIgual; i++) {
            if (nombreHerramienta.equalsIgnoreCase(equipo[i])) {
                esIgual = true;
            }
            if (equipo[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            equipo[hayEspacio] = nombreHerramienta;

        } else {
            throw new CultivoException("No se ha podido añadir la herramienta");
        }
    }

    // Hacemos un método para eliminar una herramienta
    public void eliminarHerramienta(String nombreHerramienta) throws CultivoException {
        boolean encontrado = false;
        for (int i = 0; i < equipo.length; i++) {
            if (nombreHerramienta.equalsIgnoreCase(equipo[i])) {
                encontrado = true;
                equipo[i] = null;
                break;
            }
        }
        if (!encontrado) {
            throw new CultivoException("No se ha encontrado la herramienta");
        }
    }
}