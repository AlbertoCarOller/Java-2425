package Restaurante;

import utils.MiEntradaSalida;

public class RestauranteApp {
    public static void main(String[] args) {

        // Creamos el restaurante
        Restaurante restaurante = new Restaurante("Casa Betis");

        int op;

        do {
            System.out.println("1. Crea un pedido");
            System.out.println("2. Añade un plato al pedido");
            System.out.println("3. Calcular el total de un pedido");
            System.out.println("4. Añade un plato al menú");
            System.out.println("5. Mostrar todos los platos del menú");
            System.out.println("6. Cambiar el estado de un pedido");
            System.out.println("7. Mostrar la lista de pedidos");
            System.out.println("8. Salir");

            op = MiEntradaSalida.solicitarEnteroPositivo("Introduce una opción");

            switch (op) {

                case 1:
                    try {
                        String clientePedido = MiEntradaSalida.solicitarCadena("Introduce el nombre del cliente");
                        String estadoPedido = MiEntradaSalida.solicitarCadena("Introduce el estado del pedido");
                        Pedido pedido = new Pedido(clientePedido, estadoPedido);
                        restaurante.anadirPedido(pedido);

                    } catch (PlatoExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    // TODO: No crear un pedido cada vez que quiera añadir un plato
                    try {
                        mostrarMenuPedidos(restaurante);

                        int indice = MiEntradaSalida.solicitarEnteroPositivo("Elija un pedido");

                        if (indice > restaurante.getPedidos().length) {
                            System.out.println("No has seleccionado ningún pedido");

                        } else {
                            String nombrePlatoPedido = MiEntradaSalida.solicitarCadena("Introduce el nombre del plato");
                            double precioPlatoPedido = MiEntradaSalida.solicitarDoublePositivo("Introduce el precio");
                            int tiempoPlatoPedido = MiEntradaSalida.solicitarEnteroPositivo("Introduce el tiempo");
                            Plato plato = new Plato(nombrePlatoPedido, precioPlatoPedido, tiempoPlatoPedido);
                            restaurante.getPedidos()[indice].anadirPlato(plato, restaurante);
                        }
                    } catch (PlatoExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:

                    mostrarMenuPedidos(restaurante);

                    int indice = MiEntradaSalida.solicitarEnteroPositivo("Elija un pedido");

                    if (indice > restaurante.getPedidos().length) {
                        System.out.println("No has seleccionado ninguna opción");

                    } else {
                        if (restaurante.getPedidos()[indice] != null) {
                            restaurante.getPedidos()[indice].calcularTotal();

                        } else {
                            System.out.println("No se puede calcular");
                        }
                    }
                    break;

                case 4:
                    try {
                        String nombrePlatoPedido = MiEntradaSalida.solicitarCadena("Introduce el nombre del plato");
                        double precioPlatoPedido = MiEntradaSalida.solicitarDoublePositivo("Introduce el precio");
                        int tiempoPlatoPedido = MiEntradaSalida.solicitarEnteroPositivo("Introduce el tiempo");
                        Plato plato = new Plato(nombrePlatoPedido, precioPlatoPedido, tiempoPlatoPedido);

                        restaurante.anadirPlatoAMenu(plato);

                    } catch (PlatoExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    restaurante.mostrarMenu();
                    break;

                case 6:
                    try {
                        mostrarMenuPedidos(restaurante);
                        String estadoPedido = MiEntradaSalida.solicitarCadena("Introduce el estado del pedido");

                        int indice2 = MiEntradaSalida.solicitarEnteroPositivo("Elija un pedido");

                        if (indice2 > restaurante.getPedidos().length) {
                            System.out.println("No has seleccionado ninguna opción");

                        } else {
                            if (restaurante.getPedidos()[indice2] != null) {
                                restaurante.cambiarEstadoPedido(indice2, estadoPedido);

                            } else {
                                System.out.println("No se puede cambiar");
                            }
                        }

                    } catch (PlatoExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    restaurante.mostrarPedido();
                    break;

                case 8:
                    System.out.println("Adiós");
                    break;

                default:
                    System.out.println("No has seleccionado ninguna opción");
                    break;
            }
        } while (op != 7);
    }

    public static void mostrarMenuPedidos(Restaurante restaurante) {

        for (int i = 0; i < restaurante.getPedidos().length; i++) {

            if (restaurante.getPedidos()[i] != null) {
                System.out.println(i + ". " + restaurante.getPedidos()[i]);

            } else {
                System.out.println(i + ". Espacio sin pedido");
            }
        }
    }
}