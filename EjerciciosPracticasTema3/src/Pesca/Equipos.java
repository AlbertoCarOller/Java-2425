package Pesca;

import java.util.Arrays;
import java.util.Objects;

public class Equipos {

    // Creamos los atributos
    private String nombre;
    private String region;
    private static final int NUM_PESCADORES = 4;
    private Pescador[] pescadores;

    // Creamos el constructor
    public Equipos(String nombre, String region) {
        this.nombre = nombre;
        this.region = region;
        this.pescadores = new Pescador[NUM_PESCADORES];
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Pescador[] getPescadores() {
        return pescadores;
    }

    public void setPescadores(Pescador[] pescadores) {
        this.pescadores = pescadores;
    }

    // Hacemos un equals, dos equipos serán iguales si tienen el mismo nombre
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipos equipos = (Equipos) o;
        return Objects.equals(nombre, equipos.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Equipos{" +
                "nombre='" + nombre + '\'' +
                ", region='" + region + '\'' +
                ", pescadores=" + Arrays.toString(pescadores) +
                '}';
    }

    // Hacemos un método para añadir un pescador a un equipo
    public void anadirPescador(Pescador pescador) throws PescaException {

        boolean esIgual = false;
        int hayEspacio = -1;

        for (int i = 0; i < pescadores.length && !esIgual; i++) {

            if (pescador.equals(pescadores[i])) {
                esIgual = true;
            }

            if (pescadores[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (!esIgual && hayEspacio != -1) {
            pescadores[hayEspacio] = pescador;

        } else {
            throw new PescaException("No se puede añadir el pescador");
        }
    }

    // Hacemos un método para comprobar el peso total capturado por un equipo
    public int consultarPesoEquipo() {

        int pesoTotal = 0;

        for (int i = 0; i < pescadores.length; i++) {

            if (pescadores[i] == null) {
                continue;
            }

            for (int j = 0; j < pescadores[i].getCapturasPeso().length; j++) {

                if (pescadores[i].getCapturasPeso()[j] == 0) {
                    continue;
                }
                pesoTotal += pescadores[i].getCapturasPeso()[j];
            }
        }
        return pesoTotal;
    }
}