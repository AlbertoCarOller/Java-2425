package Pesca;

import java.util.Arrays;

public class Campeonato {

    // Creamos los atributos
    private String nombre;
    private static final int MAX_EQUIPOS_PARTICIPANTES = 4;
    private Equipos[] equiposParticipantes;

    // Creamos un constructor
    public Campeonato(String nombre) {
        this.nombre = nombre;
        this.equiposParticipantes = new Equipos[MAX_EQUIPOS_PARTICIPANTES];
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Equipos[] getEquiposParticipantes() {
        return equiposParticipantes;
    }

    private void setEquiposParticipantes(Equipos[] equiposParticipantes) {
        this.equiposParticipantes = equiposParticipantes;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Campeonato{" +
                "nombre='" + nombre + '\'' +
                ", equiposParticipantes=" + Arrays.toString(equiposParticipantes) +
                '}';
    }

    // Hacemos un método para dar de alta equipos en el campeonato
    public void darAltaEquipo(Equipos equipo) throws PescaException {

        boolean esIgual = false;
        int hayEspacio = -1;

        for (int i = 0; i < equiposParticipantes.length && !esIgual; i++) {

            if (equipo.equals(equiposParticipantes[i])) {
                esIgual = true;
            }

            if (equiposParticipantes[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (!esIgual && hayEspacio != -1) {
            equiposParticipantes[hayEspacio] = equipo;

        } else {
            throw new PescaException("No se ha podido guardar el equipo");
        }
    }

    // Hacemos un método para comprobar antes de dar de alta un equipo que tenga un capitán y todos los miembros
    public boolean comprobarMiembros(Equipos equipo) {

        int contadorCapitanes = 0;

        for (int i = 0; i < equipo.getPescadores().length; i++) {

            if (equipo.getPescadores()[i] == null) {
                return false;
            }

            if (equipo.getPescadores()[i].isCapitan()) {
                contadorCapitanes++;
            }
        }

        if (contadorCapitanes != 1) {
            return false;
        }

        return true;
    }

    // Hacemos un método para comprobar el pescador con la mayor captura
    public void mayorCapturaPescador() throws PescaException {

        int mayor = 0;
        int indiceEquipo = 0;
        int indicePescador = 0;
        int indiceCaptura = 0;

        for (int i = 0; i < equiposParticipantes.length; i++) {

            if (equiposParticipantes[i] == null) {
                continue;
            }

            for (int j = 0; j < equiposParticipantes[i].getPescadores().length; j++) {

                if (equiposParticipantes[i].getPescadores()[j] == null) {
                    continue;
                }

                for (int k = 0; k < equiposParticipantes[i].getPescadores()[j].getCapturasPeso().length; k++) {

                    if (equiposParticipantes[i].getPescadores()[j].getCapturasPeso()[k] == 0) {
                        continue;
                    }

                    if (mayor < equiposParticipantes[i].getPescadores()[j].getCapturasPeso()[k]) {
                        mayor = equiposParticipantes[i].getPescadores()[j].getCapturasPeso()[k];
                        indiceEquipo = i;
                        indicePescador = j;
                        indiceCaptura = k;
                    }
                }
            }
        }

        if (mayor != 0) {
            System.out.println("El pescador con la mayor captura es " + equiposParticipantes[indiceEquipo]
                    .getPescadores()[indicePescador].getNombre() + " con un peso de " + equiposParticipantes[indiceEquipo]
                    .getPescadores()[indicePescador].getCapturasPeso()[indiceCaptura] + " kilos");

        } else {
            throw new PescaException("No se han encontrado datos");
        }
    }

    // Hacemos un método para calcular el equipo con mayor captura total en peso
    public void calcularEquipoGanador() throws PescaException {

        int mayor = 0;
        int indiceEquipo = 0;
        int sumaPesoEquipo;

        for (int i = 0; i < equiposParticipantes.length; i++) {

            if (equiposParticipantes[i] == null) {
                continue;
            }

            // Cuando vaya a calcular el peso total de un nuevo equipo, la suma la igualamos a 0
            sumaPesoEquipo = 0;

            for (int j = 0; j < equiposParticipantes[i].getPescadores().length; j++) {

                if (equiposParticipantes[i].getPescadores()[j] == null) {
                    continue;
                }

                for (int k = 0; k < equiposParticipantes[i].getPescadores()[j].getCapturasPeso().length; k++) {

                    if (equiposParticipantes[i].getPescadores()[j].getCapturasPeso()[k] == 0) {
                        continue;
                    }

                    sumaPesoEquipo += equiposParticipantes[i].getPescadores()[j].getCapturasPeso()[k];
                }
            }

            if (mayor < sumaPesoEquipo) {
                mayor = sumaPesoEquipo;
                indiceEquipo = i;
            }
        }

        if (mayor != 0) {
            System.out.println("El equipo con mayor captura es " + equiposParticipantes[indiceEquipo].getNombre());

        } else {
            throw new PescaException("No se han encontrado datos de captura");
        }
    }
}