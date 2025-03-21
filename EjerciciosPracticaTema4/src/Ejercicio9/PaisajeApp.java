package Ejercicio9;

import utils.MiEntradaSalida;

public class PaisajeApp {
    // Creamos un paisaje
    static Paisaje paisaje = new Paisaje();

    public static void main(String[] args) {
        int op = 0;
        do {
            try {
                op = MiEntradaSalida.seleccionarOpcion("Selecciona una opción", new String[]{"Añadir un elemento al paisaje",
                        "Conectar dos elementos del paisaje", "Calcular el valor total del paisaje", "Ver elementos", "Salir"});

                switch (op) {
                    case 1:
                        int op2 = MiEntradaSalida.seleccionarOpcion("Selecciona un elemento a añadir", new String[]{"Añadir Pino",
                                "Añadir Roble", "Añadir Río", "Añadir Lago Dulce", "Añadir Lago Salado", "Añadir Montaña Nevada",
                                "Añadir Montaña Rocosa"});
                        String nombreElemento = MiEntradaSalida.solicitarCadena("Introduce el nombre");

                        switch (op2) {
                            case 1:
                                int edadPino = MiEntradaSalida.solicitarEnteroPositivo("Introduce la edad del pino");
                                String tipoPinaSt = MiEntradaSalida.solicitarCadena("Introduce el tipo de piña");
                                paisaje.anadirElemento(new Pino(nombreElemento, edadPino, Pina.valueOf(tipoPinaSt.toUpperCase())));
                                break;

                            case 2:
                                double alturaRoble = MiEntradaSalida.solicitarDoublePositivo("Introduce la altura del pino");
                                int opFrutos = MiEntradaSalida.seleccionarOpcion("¿Tiene frutos?", new String[]{"Sí", "No"});
                                boolean frutos;
                                if (opFrutos == 1) {
                                    frutos = true;

                                } else {
                                    frutos = false;
                                }
                                paisaje.anadirElemento(new Roble(nombreElemento, alturaRoble, frutos));
                                break;

                            case 3:
                                String tipoAguaSt = MiEntradaSalida.solicitarCadena("Introduce el tipo de agua del río");
                                int caudal = MiEntradaSalida.solicitarEnteroPositivo("Introduce los litros del caudal");
                                int opCaudal = MiEntradaSalida.seleccionarOpcion("¿Tiene caudal?", new String[]{"Sí", "No"});
                                boolean cataratas;
                                if (opCaudal == 1) {
                                    cataratas = true;

                                } else {
                                    cataratas = false;
                                }
                                paisaje.anadirElemento(new Rio(nombreElemento, Agua.valueOf(tipoAguaSt.toUpperCase()), caudal, cataratas));
                                break;

                            case 4:
                                int profundidadMaxima = MiEntradaSalida.solicitarEnteroPositivo("Introduce la profundidad máxima");
                                int opIslas = MiEntradaSalida.seleccionarOpcion("¿Tiene islas?", new String[]{"Sí", "No"});
                                boolean islas;
                                if (opIslas == 1) {
                                    islas = true;

                                } else {
                                    islas = false;
                                }
                                paisaje.anadirElemento(new LagoDulce(nombreElemento, profundidadMaxima, islas));
                                break;

                            case 5:
                                int sanilidad = MiEntradaSalida.solicitarEnteroPositivo("Introduce la sanilidad");
                                int opEndemica = MiEntradaSalida.seleccionarOpcion("¿Hay especies endémicas?", new String[]{"Sí", "No"});
                                boolean endemicas;
                                if (opEndemica == 1) {
                                    endemicas = true;

                                } else {
                                    endemicas = false;
                                }
                                paisaje.anadirElemento(new LagoSalado(nombreElemento, sanilidad, endemicas));
                                break;

                            case 6:
                                int cantidadNieveAcumulada = MiEntradaSalida.solicitarEnteroPositivo("Introduce la cantidad de nieve");
                                String tipoFloraSt = MiEntradaSalida.solicitarCadena("Introduce el tipo de flora");
                                tipoFloraSt = tipoFloraSt.toUpperCase();
                                paisaje.anadirElemento(new MontanaNevada(nombreElemento, cantidadNieveAcumulada, Flora.valueOf(tipoFloraSt.toUpperCase())));
                                break;

                            case 7:
                                int altitud = MiEntradaSalida.solicitarEnteroPositivo("Introduce la altitud");
                                int opCavernas = MiEntradaSalida.seleccionarOpcion("¿Tiene cavernas?", new String[]{"Sí", "No"});
                                boolean cavernas;
                                if (opCavernas == 1) {
                                    cavernas = true;

                                } else {
                                    cavernas = false;
                                }
                                paisaje.anadirElemento(new MontanaRocosa(nombreElemento, altitud, cavernas));
                                break;

                            default:
                                System.out.println("No has elegido ninguna opción");
                                break;
                        }
                        break;

                    case 2:
                        String nombreElementoABuscar = MiEntradaSalida.solicitarCadena("Introduce el tipo de" +
                                " elemento a buscar seguido del nombre");
                        ElementoPaisaje elementoPaisaje = buscarElemento(nombreElementoABuscar);
                        String nombreElementoAConectar = MiEntradaSalida.solicitarCadena("Introduce el nombre del elemento a conectar");
                        elementoPaisaje.conectar(buscarElemento(nombreElementoAConectar));
                        break;

                    case 3:
                        System.out.println("El valor total del paisaje es de " + paisaje.calcularValorPaisaje());
                        break;

                    case 4:
                        System.out.println(paisaje);
                        break;

                    case 5:
                        System.out.println("Hasta pronto");
                        break;

                    default:
                        System.out.println("No has seleccionado ninguna opción");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Has introducido un enum inválido");

            } catch (ElementoException e) {
                System.out.println(e.getMessage());
            }

        } while (op != 5);
    }

    // Hacemos un método para buscar un elemento
    public static ElementoPaisaje buscarElemento(String nombreElemento) throws ElementoException {
        ElementoPaisaje elementoPaisajeEncontrado = null;
        boolean encontrado = false;
        for (ElementoPaisaje elementoPaisaje : paisaje.getElementosPaisajes()) {
            if (elementoPaisaje != null) {
                if (nombreElemento.equalsIgnoreCase(elementoPaisaje.getNombre())) {
                    elementoPaisajeEncontrado = elementoPaisaje;
                    encontrado = true;
                    break;
                }
            }
        }
        if (!encontrado) {
            throw new ElementoException("No se ha encontrado el elemento");
        }
        return elementoPaisajeEncontrado;
    }
}