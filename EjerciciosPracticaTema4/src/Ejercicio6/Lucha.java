package Ejercicio6;

import java.util.Arrays;

public class Lucha {

    // Creamos los atributos
    private Luchador[] luchadores;
    private static final int MAX_LUCHADORES = 2;

    // Hacemos el constructor
    public Lucha() {
        this.luchadores = new Luchador[MAX_LUCHADORES];
    }

    // Hacemos un get y set
    public Luchador[] getLuchadores() {
        return luchadores;
    }

    private void setLuchadores(Luchador[] luchadores) {
        this.luchadores = luchadores;
    }

    // Hacemos un método para añadir un luchador a la lucha
    public void anadirLuchador(Luchador luchador) throws LuchadorException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < luchadores.length && !esIgual; i++) {
            if (luchador.equals(luchadores[i])) {
                esIgual = true;
            }

            if (luchadores[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            luchadores[hayEspacio] = luchador;

        } else {
            throw new LuchadorException("No se puede introducir este luchador");
        }
    }

    // Hacemos un método para simular una lucha
    public void comenzarLucha() throws LuchadorException {
        for (int i = 0; i < luchadores.length; i++) {
            if (luchadores[i] == null) {
                throw new LuchadorException("Faltan luchadores, no puede comenzar la lucha");
            }
        }
        while (luchadores[0].getSalud() != 0 && luchadores[1].getSalud() != 0) {
            if (luchadores[0].getVelocidad() > luchadores[1].getVelocidad()) {
                if (luchadores[0] instanceof Atacante atacante) {
                    atacante.atacar(luchadores[1]);

                } else {
                    luchadores[0].atacar(luchadores[1]);
                    ((Medico) luchadores[0]).curar();
                }
                if (luchadores[1] instanceof Atacante atacante) {
                    atacante.atacar(luchadores[0]);

                } else {
                    luchadores[1].atacar(luchadores[0]);
                    ((Medico) luchadores[1]).curar();
                }
            } else if (luchadores[1].getVelocidad() > luchadores[0].getVelocidad()) {
                if (luchadores[1] instanceof Atacante atacante) {
                    atacante.atacar(luchadores[0]);

                } else {
                    luchadores[1].atacar(luchadores[0]);
                    ((Medico) luchadores[1]).curar();
                }
                if (luchadores[0] instanceof Atacante atacante) {
                    atacante.atacar(luchadores[1]);

                } else {
                    luchadores[0].atacar(luchadores[1]);
                    ((Medico) luchadores[0]).curar();
                }
            } else {
                throw new LuchadorException("No pueden luchar, los dos son tan lentos que nunca terminarían");
            }
        }
        if (luchadores[0].getSalud() == 0) {
            System.out.println(luchadores[1].getNombre() + " ha ganado");

        } else {
            System.out.println(luchadores[0].getNombre() + " ha ganado");
        }
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Lucha{" +
                "luchadores=" + Arrays.toString(luchadores) +
                '}';
    }
}