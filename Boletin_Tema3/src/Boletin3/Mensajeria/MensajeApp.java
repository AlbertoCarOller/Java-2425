package Boletin3.Mensajeria;

import utils.MiEntradaSalida;

public class MensajeApp {
    public static void main(String[] args) {

        // Creamos los objetos personas
        Persona p1 = new Persona("Alberto");
        Persona p2 = new Persona("Lolo");

        int op;
        // Creamos un menú
        do {
            System.out.println("1. Enviar mensaje");
            System.out.println("2. Eliminar mensaje más antiguo enviado");
            System.out.println("3. Eliminar mensaje más antiguo recibido");
            System.out.println("4. Salir");

            op = MiEntradaSalida.solicitarEnteroPositivo("Seleccione:");

            switch (op) {

                case 1:
                    int op2 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 si eres " + p1.getNombre() +
                            ", 2 si eres " + p2.getNombre());
                    switch (op2) {

                        case 1:
                            try {
                                String asunto = MiEntradaSalida.solicitarCadena("Introduce el asunto");
                                String cuerpo = MiEntradaSalida.solicitarCadena("Introduce el cuerpo");
                                p1.enviarMensaje(p2, asunto, cuerpo);

                            } catch (MensajeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                String asunto = MiEntradaSalida.solicitarCadena("Introduce el asunto");
                                String cuerpo = MiEntradaSalida.solicitarCadena("Introduce el cuerpo");
                                p2.enviarMensaje(p1, asunto, cuerpo);

                            } catch (MensajeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        default:
                            System.out.println("No has seleccionado nada");
                            break;
                    }
                    break;

                case 2:
                    int op3 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 si eres " + p1.getNombre() +
                            ", 2 si eres " + p2.getNombre());
                    switch (op3) {

                        case 1:
                            try {
                                p1.borrarMensajeEnviadoMasAntiguo();

                            } catch (MensajeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                p2.borrarMensajeEnviadoMasAntiguo();

                            } catch (MensajeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        default:
                            System.out.println("No has seleccionado ninguna opción");
                            break;

                    }
                    break;

                case 3:
                    int op4 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 si eres " + p1.getNombre() +
                            ", 2 si eres " + p2.getNombre());
                    switch (op4) {

                        case 1:
                            try {
                                p1.borrarMensajeRecibidoMasAntiguo();

                            } catch (MensajeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                p2.borrarMensajeRecibidoMasAntiguo();

                            } catch (MensajeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                    }
                    break;

                case 4:
                    System.out.println("Hasta pronto");
                    break;

                default:
                    System.out.println("No has seleccionado ninguna opción");
                    break;
            }
        } while (op != 4);
    }
}