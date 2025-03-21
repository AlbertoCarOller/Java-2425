package Pesca;

import utils.MiEntradaSalida;

public class PescaApp {
    public static void main(String[] args) {

        Campeonato campeonato = new Campeonato("Campeonato Pesca 24/25");

        int op = 0;

        do {
            try {
                String[] menu = {"Registrar nuevo equipo",
                        "Añadir pescador a equipo",
                        "Registrar una captura",
                        "Peso total de un equipo",
                        "Consultar pescador con la mayor captura",
                        "Consultar equipo con el mayor peso acumulado",
                        "Salir"};

                op = MiEntradaSalida.seleccionarOpcion("Elija:", menu);

                switch (op) {
                    case 1:
                        String nombreEquipoRegistrar = MiEntradaSalida.solicitarCadena("Introduce el nombre del equipo");
                        String nombreRegionRegistrar = MiEntradaSalida.solicitarCadena("Introduce el nombre de la región");
                        Equipos equipo = new Equipos(nombreEquipoRegistrar, nombreRegionRegistrar);
                        campeonato.darAltaEquipo(equipo);
                        break;

                    case 2:
                        mostrarMenuEquipos(campeonato);
                        int op2 = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");
                        op2 = op2 - 1;

                        if (op2 < 0 || op2 >= campeonato.getEquiposParticipantes().length) {
                            System.out.println("Has seleccionado una opción fuera de rango");

                        } else if (campeonato.getEquiposParticipantes()[op2] == null) {
                            System.out.println("Has seleccionado una opción no válida");

                        } else {
                            String nombrePescador = MiEntradaSalida.solicitarCadena("Introduce el nombre del pescador");
                            int edadPescador = MiEntradaSalida.solicitarEnteroPositivo("Introduce la edad del pescador");
                            int experienciaPescador = MiEntradaSalida.solicitarEnteroPositivo("Introduce la experiencia laboral");
                            char respuesta = MiEntradaSalida.solicitarCaracterSN("¿Eres capitán? (S/N)");
                            boolean esCapitan = esCapitan(respuesta);
                            Pescador pescador = new Pescador(nombrePescador, edadPescador, experienciaPescador, esCapitan);
                            campeonato.getEquiposParticipantes()[op2].anadirPescador(pescador);
                        }
                        break;

                    case 3:
                        mostrarMenuEquipos(campeonato);
                        int op3 = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");
                        op3 = op3 - 1;

                        if (op3 < 0 || op3 >= campeonato.getEquiposParticipantes().length) {
                            System.out.println("Has seleccionado una opción fuera de rango");

                        } else if (campeonato.getEquiposParticipantes()[op3] == null) {
                            System.out.println("Has seleccionado una opción no válida");

                        } else {

                            if (!campeonato.comprobarMiembros(campeonato.getEquiposParticipantes()[op3])) {
                                System.out.println("No se puede calcular, el equipo no está completado, borrando equipo...");
                                campeonato.getEquiposParticipantes()[op3] = null;

                            } else {
                                mostrarMenuPescadores(campeonato.getEquiposParticipantes()[op3]);
                                int opSeg3 = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");
                                opSeg3 = opSeg3 - 1;

                                if (opSeg3 < 0 || opSeg3 >= campeonato.getEquiposParticipantes().length) {
                                    System.out.println("Has seleccionado una opción fuera de rango");

                                } else if (campeonato.getEquiposParticipantes()[opSeg3] == null) {
                                    System.out.println("Has seleccionado una opción no válida");

                                } else {
                                    int pesoCaptura = MiEntradaSalida.solicitarEnteroPositivo("Introduce el peso de la captura");
                                    campeonato.getEquiposParticipantes()[op3].getPescadores()[opSeg3].anadirCaptura(pesoCaptura);
                                }
                            }
                        }
                        break;

                    case 4:
                        mostrarMenuEquipos(campeonato);
                        int op4 = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");
                        op4 = op4 - 1;

                        if (op4 < 0 || op4 >= campeonato.getEquiposParticipantes().length) {
                            System.out.println("Has seleccionado una opción fuera de rango");

                        } else if (campeonato.getEquiposParticipantes()[op4] == null) {
                            System.out.println("Has seleccionado una opción no válida");

                        } else {

                            if (!campeonato.comprobarMiembros(campeonato.getEquiposParticipantes()[op4])) {
                                System.out.println("No se puede calcular, el equipo no está completando, borrando equipo...");
                                campeonato.getEquiposParticipantes()[op4] = null;

                            } else {
                                int pesoTotalEquipo = campeonato.getEquiposParticipantes()[op4].consultarPesoEquipo();
                                System.out.println("El peso total del equipo es de " + pesoTotalEquipo);
                            }
                        }
                        break;

                    case 5:
                        if (consultarTodosLosMiembros(campeonato) != -1) {
                            System.out.println("Los equipos no están correctos, borrando equipo...");
                            campeonato.getEquiposParticipantes()[consultarTodosLosMiembros(campeonato)] = null;

                        } else {
                            campeonato.mayorCapturaPescador();
                        }
                        break;

                    case 6:
                        if (consultarTodosLosMiembros(campeonato) != -1) {
                            System.out.println("Los equipos no están correctos, borrando equipo...");
                            campeonato.getEquiposParticipantes()[consultarTodosLosMiembros(campeonato)] = null;

                        } else {
                            campeonato.calcularEquipoGanador();
                        }
                        break;

                    case 7:
                        System.out.println("Hasta pronto");
                        break;
                }
            } catch (PescaException e) {
                System.out.println(e.getMessage());
            }
        } while (op != 7);
    }

    // Hacemos un método para mostrar todos los equipos
    public static void mostrarMenuEquipos(Campeonato campeonato) {

        for (int i = 0; i < campeonato.getEquiposParticipantes().length; i++) {

            if (campeonato.getEquiposParticipantes()[i] == null) {
                System.out.println(i + 1 + ". No hay equipo");

            } else {
                System.out.println(i + 1 + "." + campeonato.getEquiposParticipantes()[i].getNombre());
            }
        }
    }

    // Hacemos un método para determinar si un pescador es capitán
    public static boolean esCapitan(char respuesta) {

        if (respuesta == 'S') {
            return true;
        }
        return false;
    }

    // Hacemos un método para mostrar los pescadores de un equipo
    public static void mostrarMenuPescadores(Equipos equipo) {

        for (int i = 0; i < equipo.getPescadores().length; i++) {

            if (equipo.getPescadores()[i] == null) {
                System.out.println(i + 1 + ". No hay pescador");

            } else {
                System.out.println(i + 1 + ". " + equipo.getPescadores()[i]);
            }
        }
    }

    // Hacemos un método para poder repasar los miembros de todos los equipos
    public static int consultarTodosLosMiembros(Campeonato campeonato) {

        for (int i = 0; i < campeonato.getEquiposParticipantes().length; i++) {

            if (campeonato.getEquiposParticipantes()[i] == null) {
                continue;
            }

            if (!campeonato.comprobarMiembros(campeonato.getEquiposParticipantes()[i])) {
                return i;
            }
        }
        return -1;
    }
}