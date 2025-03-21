package Restaurante;

import java.util.Arrays;

public class Restaurante {

    // Atributos
    private String nombre;
    private Plato[] menu = new Plato[4];
    private Pedido[] pedidos = new Pedido[3];

    // Hacemos el constructor
    public Restaurante(String nombre) {
        this.nombre = nombre;
    }

    // Hacemos los get
    public String getNombre() {
        return nombre;
    }

    public Plato[] getMenu() {
        return menu;
    }

    public Pedido[] getPedidos() {
        return pedidos;
    }

    // Hacemos un método para añadir un plato al menú
    public void anadirPlatoAMenu(Plato plato) throws PlatoExcepcion {

        boolean mismoPlato = false;
        int hayEspacio = -1;

        for (int i = 0; i < menu.length; i++) {

            if (plato.equals(menu[i])) {
                mismoPlato = true;
            }

            if (menu[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (!mismoPlato && hayEspacio != -1) {
            menu[hayEspacio] = plato;
        }

        if (hayEspacio == -1) {
            throw new PlatoExcepcion("No hay espacio en el menú");
        }

        if (mismoPlato) {
            throw new PlatoExcepcion("El plato ya está en el menú");
        }
    }

    // Hacemos un método para mostrar todos los platos del menú
    public void mostrarMenu() {

        System.out.println(Arrays.toString(menu));
    }

    // Hacemos un método para añadir un pedido a la lista de pedidos
    public void anadirPedido(Pedido pedido) throws PlatoExcepcion {

        boolean pedidosIguales = false;
        int hayEspacio = -1;

        for (int i = 0; i < pedidos.length; i++) {

            if (pedido.equals(pedidos[i])) {
                pedidosIguales = true;
            }

            if (pedidos[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (!pedidosIguales && hayEspacio != -1) {
            pedidos[hayEspacio] = pedido;
        }

        if (hayEspacio == -1) {
            throw new PlatoExcepcion("No hay espacio");
        }

        if (pedidosIguales) {
            throw new PlatoExcepcion("El pedido ya está en la lista");
        }
    }

    // Hacemos un método para mostrar los pedido
    public void mostrarPedido() {

        System.out.println(Arrays.toString(pedidos));
    }

    // Hacemos un método para cambiar el estado de un pedido
    public void cambiarEstadoPedido(int indice, String estado) throws PlatoExcepcion {

        if (indice > pedidos.length) {
            throw new PlatoExcepcion("No ha seleccionado una opción válida");
        }

        if (!estado.equals("en proceso") && !estado.equals("entregado")) {
            throw new PlatoExcepcion("Este estado no existe");
        }

        pedidos[indice].setEstado(estado);
    }
}