package Ejercicio12;

import java.util.Arrays;
import java.util.Objects;

public class Estacion {

    // Creamos los atributos
    private String nombre;
    private Modulo[] modulos;
    private static final int MAX_MODULOS = 4;
    private Ingeniero[] ingenieros;
    private static final int MAX_INGENIEROS = 6;

    // Creamos el constructor
    public Estacion(String nombre) {
        this.nombre = nombre;
        this.modulos = new Modulo[MAX_MODULOS];
        this.ingenieros = new Ingeniero[MAX_INGENIEROS];
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Modulo[] getModulos() {
        return modulos;
    }

    private void setModulos(Modulo[] modulos) {
        this.modulos = modulos;
    }

    public Ingeniero[] getIngenieros() {
        return ingenieros;
    }

    private void setIngenieros(Ingeniero[] ingenieros) {
        this.ingenieros = ingenieros;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estacion estacion = (Estacion) o;
        return Objects.equals(nombre, estacion.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Creamos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Módulos: %s, Ingenieros: %s", this.nombre, Arrays.toString(this.modulos), Arrays.toString(this.ingenieros));
    }

    // Hacemos un método para añadir módulos a la estación
    public void anadirModulo(Modulo modulo) throws ModuloException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < modulos.length && !esIgual; i++) {
            if (modulo.equals(modulos[i])) {
                esIgual = true;
            }
            if (modulos[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            modulos[hayEspacio] = modulo;

        } else {
            throw new ModuloException("No se puede añadir el módulo");
        }
    }

    // Hacemos un método para añadir un ingeniero
    public void anadirIngeniero(Ingeniero ingeniero) throws ModuloException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < ingenieros.length && !esIgual; i++) {
            if (ingeniero.equals(ingenieros[i])) {
                esIgual = true;
            }
            if (ingenieros[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            ingenieros[hayEspacio] = ingeniero;

        } else {
            throw new ModuloException("No se piede añadir al ingeniero");
        }
    }

    // Hacemos un método para eliminar un ingeniero
    public void eliminarIngeniero(Ingeniero ingeniero) throws ModuloException {
        boolean encontrado = false;
        for (int i = 0; i < ingenieros.length; i++) {
            if (ingeniero.equals(ingenieros[i])) {
                encontrado = true;
                ingenieros[i] = null;
                break;
            }
        }
        if (!encontrado) {
            throw new ModuloException("No se ha encontrado el ingeniero");
        }
    }

    // Hacemos un método que va a simular un mes, durante este periodo puede bajar la salud y aumentar el envejecimiento
    public void periodo() throws ModuloException {
        boolean accidente;
        int probabilidadAccidente = (int) (Math.random() * 100) + 1;
        if (probabilidadAccidente >= 50) {
            accidente = true;
            System.out.println("Ha pasado un mes con accidentes");

        } else {
            accidente = false;
            System.out.println("¡Felicidades ha pasado un mes sin accidentes!");
        }
        quitarVida(accidente);
        sumarEnvejecimiento(accidente);
    }

    // Hacemos un método complementario de 'periodo' que va a quitar salud a los módulos
    private void quitarVida(boolean accidente) throws ModuloException {
        int vidaAQuitar;
        for (int i = 0; i < modulos.length; i++) {
            if (modulos[i] == null) {
                continue;
            }
            if (accidente) {
                vidaAQuitar = 20;

            } else {
                vidaAQuitar = 10;
            }
            if ((modulos[i].getSalud() - vidaAQuitar) <= 0) {
                System.out.println("El módulo " + modulos[i].getNombre() + " ha muerto");
                modulos[i] = null;

            } else {
                modulos[i].setSalud(modulos[i].getSalud() - vidaAQuitar);
            }
        }
    }

    // Hacemos un método complementario de 'periodo' que va a sumar envejecimiento a los módulos
    private void sumarEnvejecimiento(boolean accidente) throws ModuloException {
        int envejecimientoASumar;
        for (int i = 0; i < modulos.length; i++) {
            if (modulos[i] == null) {
                continue;
            }
            if (accidente) {
                envejecimientoASumar = 10;

            } else {
                envejecimientoASumar = 20;
            }
            if ((modulos[i].getEnvejecimiento() + envejecimientoASumar) >= 100) {
                System.out.println("El módulo " + modulos[i].getNombre() + " ha muerto");
                modulos[i] = null;

            } else {
                modulos[i].setEnvejecimiento(modulos[i].getEnvejecimiento() + envejecimientoASumar);
            }
        }
    }
}