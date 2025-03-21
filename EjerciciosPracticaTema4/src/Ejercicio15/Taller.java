package Ejercicio15;

import java.util.Arrays;
import java.util.Objects;

public class Taller {

    // Creamos los atributos
    private String nombre;
    private Pieza[] piezas;
    private static final int MAX_PIEZAS = 10;

    // Creamos el constructor
    public Taller(String nombre) {
        this.nombre = nombre;
        this.piezas = new Pieza[MAX_PIEZAS];
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pieza[] getPiezas() {
        return piezas;
    }

    private void setPiezas(Pieza[] piezas) {
        this.piezas = piezas;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taller taller = (Taller) o;
        return Objects.equals(nombre, taller.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Creamos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Piezas: %s", this.nombre, Arrays.toString(this.piezas));
    }

    // Hacemos un método para añadir una pieza al taller
    public void registrarPieza(Pieza pieza) throws TallerExeption {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < piezas.length && !esIgual; i++) {
            if (pieza.equals(piezas[i])) {
                esIgual = true;
            }
            if (piezas[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            piezas[hayEspacio] = pieza;

        } else {
            throw new TallerExeption("No se ha podido añadir la pieza");
        }
    }
}