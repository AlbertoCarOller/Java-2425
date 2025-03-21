package Ejercicio5;

import utils.MiEntradaSalida;

import java.time.LocalDateTime;

public class EventoApp {
    // Creamos una lista de eventos
    static final int MAX_EVENTOS = 6;
    static Evento[] eventos = new Evento[MAX_EVENTOS];

    public static void main(String[] args) {
        int op = 0;
        do {
            try {
                // Creamos un menú con las opciones
                op = MiEntradaSalida.seleccionarOpcion("Seleccione una opción", new String[]{"Añadir evento",
                        "Añadir conferente", "Añadir herramienta", "Calcular precio", "Registrar asistentes", "Salir"});
                switch (op) {
                    case 1:
                        int op2 = MiEntradaSalida.seleccionarOpcion("Elija un opción", new String[]{"Añadir conferencia",
                                "Añadir taller"});
                        switch (op2) {
                            case 1:
                                String nombreConferencia = MiEntradaSalida.solicitarCadena("Introduce el nombre");
                                int ano = MiEntradaSalida.solicitarEntero("Introduce el año");
                                int mes = MiEntradaSalida.solicitarEntero("Introduce el mes");
                                int dias = MiEntradaSalida.solicitarEntero("Introduce el día");
                                int hora = MiEntradaSalida.solicitarEntero("Introduce la hora");
                                int minuto = MiEntradaSalida.solicitarEntero("Introduce los minutos");
                                String ubicacion = MiEntradaSalida.solicitarCadena("Introduce la ubicación");
                                double precioEntrada = MiEntradaSalida.solicitarDoublePositivo("introduce el precio de la entrada");
                                Evento evento = new Conferencia(nombreConferencia, LocalDateTime.of(ano, mes, dias, hora, minuto)
                                        , ubicacion, precioEntrada);
                                anadirEvento(evento);
                                break;

                            case 2:
                                String nombreTaller = MiEntradaSalida.solicitarCadena("Introduce el nombre");
                                int anoTaller = MiEntradaSalida.solicitarEntero("Introduce el año");
                                int mesTaller = MiEntradaSalida.solicitarEntero("Introduce el mes");
                                int diasTaller = MiEntradaSalida.solicitarEntero("Introduce el día");
                                int horaTaller = MiEntradaSalida.solicitarEntero("Introduce la hora");
                                int minutoTaller = MiEntradaSalida.solicitarEntero("Introduce los minutos");
                                String ubicacionTaller = MiEntradaSalida.solicitarCadena("Introduce la ubicación");
                                double precioEntradaTaller = MiEntradaSalida.solicitarDoublePositivo("introduce el precio de la entrada");
                                Evento evento1 = new Taller(nombreTaller, LocalDateTime.of(anoTaller, mesTaller, diasTaller,
                                        horaTaller, minutoTaller), ubicacionTaller, precioEntradaTaller);
                                anadirEvento(evento1);
                                break;
                        }
                        break;

                    case 2:
                        System.out.println(mostrarMenu());
                        int op3 = MiEntradaSalida.solicitarEnteroPositivo("Introduce la opción");
                        if (op3 < 1 || op3 > eventos.length || eventos[op3 - 1] == null) {
                            System.out.println("Opción inválida");

                        } else {
                            if (!(eventos[op3 - 1] instanceof Conferencia)) {
                                System.out.println("No es una conferencia");

                            } else {
                                String conferenciante = MiEntradaSalida.solicitarCadena("Introduce el nombre");
                                ((Conferencia) eventos[op3 - 1]).anadirConferenciante(conferenciante);
                                System.out.println("Añadiendo...");
                            }
                        }
                        break;

                    case 3:
                        System.out.println(mostrarMenu());
                        int op4 = MiEntradaSalida.solicitarEnteroPositivo("Elija la opción");
                        if (op4 < 1 || op4 > eventos.length || eventos[op4 - 1] == null) {
                            System.out.println("Opción inválida");

                        } else {
                            if (!(eventos[op4 - 1] instanceof Taller)) {
                                System.out.println("No es un taller");

                            } else {
                                String herramienta = MiEntradaSalida.solicitarCadena("Introduce la herramienta");
                                ((Taller) eventos[op4 - 1]).anadirHerramienta(herramienta);
                                System.out.println("Añadiendo...");
                            }
                        }
                        break;

                    case 4:
                        System.out.println(mostrarMenu());
                        int op5 = MiEntradaSalida.solicitarEnteroPositivo("Elija la opción");
                        if (op5 < 1 || op5 > eventos.length || eventos[op5 - 1] == null) {
                            System.out.println("Opción inválida");

                        } else {
                            if (eventos[op5 - 1] instanceof Comible comible) {
                                System.out.println("Lo recaudado de " + eventos[op5 - 1].getNombre() + " es de " +
                                        comible.calcularPrecioComible() + " euros");

                            } else {
                                System.out.println("Lo recaudado de " + eventos[op5 - 1].getNombre() + " es de " +
                                        ((Bebible) eventos[op5 - 1]).calcularPrecioBebible() + " euros");
                            }
                        }
                        break;

                    case 5:
                        System.out.println(mostrarMenu());
                        int op6 = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");
                        if (op6 < 1 || op6 > eventos.length || eventos[op6 - 1] == null) {
                            System.out.println("Opción inválida");

                        } else {
                            int numAsistentes = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de asistentes");
                            eventos[op6 - 1].registrarAsistentes(numAsistentes);
                            System.out.println("El evento " + eventos[op6 - 1].getNombre() + " tiene " + numAsistentes + " asistentes");
                        }
                        break;

                    case 6:
                        System.out.println("Hasta pronto");
                        break;

                    default:
                        System.out.println("No ha elegido ninguna opción");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (op != 6);
    }

    // Hacemos un método para añadir un evento a la lista de eventos
    public static void anadirEvento(Evento evento) throws EventoException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < eventos.length && !esIgual; i++) {
            if (evento.equals(eventos[i])) {
                esIgual = true;
            }
            if (eventos[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            eventos[hayEspacio] = evento;

        } else {
            throw new EventoException("No se ha podido añadir el evento");
        }
    }

    // Hacemos un método para mostrar un menú de los eventos de la lista
    public static String mostrarMenu() throws EventoException {
        int contador = 0;
        StringBuilder sb = new StringBuilder();
        for (Evento evento : eventos) {
            if (evento != null) {
                ++contador;
                sb.append(contador).append(". ").append(evento.getNombre()).append("\n");

            } else {
                ++contador;
                sb.append(contador).append(". ").append("Espacio vacío").append("\n");
            }
        }
        if (contador == 0) {
            throw new EventoException("No hay conferencias");
        }
        return sb.toString();
    }
}