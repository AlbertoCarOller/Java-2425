package Boletin2.Jarra;

import utils.MiEntradaSalida;

public class JarraApp {
    public static void main(String[] args) {

        // Creamos las dos jarras
        Jarra jarraA = new Jarra();
        Jarra jarraB = new Jarra();

        // Solicitamos por teclado la capacidad de 'jarraA'
        double capacidadJarraA = MiEntradaSalida.solicitarDoublePositivo("Introduce la capacidad" +
                " de la Jarra A");
        jarraA.setCapacidadJarra(capacidadJarraA);

        // Solicitamos por teclado la capacidad de 'jarraB'
        double capacidadJarraB = MiEntradaSalida.solicitarDoublePositivo("Introduce la capacidad" +
                " de la Jarra B");
        jarraB.setCapacidadJarra(capacidadJarraB);

        int op = 0;

        // Creamos un menú
        do {
            try {
                System.out.println("1. Llenar jarra");
                System.out.println("2. Vaciar jarra");
                System.out.println("3. Volcar jarra");
                System.out.println("4. Ver estado de las jarras");
                System.out.println("5. Salir");

                op = MiEntradaSalida.solicitarEnteroPositivo("Introduce un número para elegir");

                switch (op) {

                    case 1:
                        int op2 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 para llenar la jarra A y" +
                                " 2 para llenar la B");

                        switch (op2) {

                            case 1:
                                System.out.println("Has seleccionado la jarra A");
                                jarraA.rellenarJarra();
                                break;

                            case 2:
                                System.out.println("Has seleccionado la jarra B");
                                jarraB.rellenarJarra();
                                break;

                            default:
                                System.out.println("No has seleccionado ninguna opción");
                                break;
                        }
                        break;

                    case 2:
                        int op3 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 para vaciar la jarra A y" +
                                " 2 para vaciar la B");

                        switch (op3) {

                            case 1:
                                System.out.println("Has seleccionado la jarra A");
                                jarraA.vaciarJarra();
                                break;

                            case 2:
                                System.out.println("Has seleccionado la jarra B");
                                jarraB.vaciarJarra();
                                break;

                            default:
                                System.out.println("No has seleccionado ninguna opción");
                                break;
                        }
                        break;

                    case 3:
                        int op4 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 para volcar la jarra A en la B," +
                                " pulsa 2 para volcar la jarra B en la A");

                        switch (op4) {

                            case 1:
                                System.out.println("Has seleccionado volcar la jarra A en la B");
                                jarraA.volcarJarra(jarraB);
                                break;

                            case 2:
                                System.out.println("Has seleccioando volcar la jarra B en la A");
                                jarraB.volcarJarra(jarraA);
                                break;

                            default:
                                System.out.println("No has seleccionado ninguna opción");
                                break;
                        }
                        break;

                    case  4:
                        System.out.println(jarraA);
                        System.out.println(jarraB);
                        break;

                    case  5:
                        double litros = jarraA.getTotalAguaConsumida() + jarraB.getTotalAguaConsumida();
                        System.out.println("El total de agua que se ha gastado llenado jarras es "
                                + litros + " litros");
                }

            } catch (JarraException e) {
                System.out.println(e.getMessage());
            }

        } while (op != 5);
    }
}