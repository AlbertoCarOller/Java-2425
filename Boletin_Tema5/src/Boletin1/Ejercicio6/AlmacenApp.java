package Boletin1.Ejercicio6;

import java.util.ArrayList;

public class AlmacenApp {
    static Almacen almacen = new Almacen();
    static final int MAX_CAJA = 20;

    public static void main(String[] args) {
        crearCajas();
        try {
            // Cerramos la caja 1 y 5
            almacen.cerrarCaja(1);
            almacen.cerrarCaja(5);
            // Añadimos varios clientes
            System.out.println(almacen.anadirCliente());
            System.out.println(almacen.anadirCliente());
            System.out.println(almacen.anadirCliente());
            System.out.println(almacen.anadirCliente());
            System.out.println(almacen.anadirCliente());
            System.out.println(almacen.anadirCliente());
            System.out.println(almacen.anadirCliente());
            System.out.println(almacen.anadirCliente());
            System.out.println(almacen.anadirCliente());
            System.out.println(almacen.anadirCliente());
            // Mostramos el almacén
            System.out.println(almacen);
            // Atendemos a clientes
            System.out.println(almacen.atenderCliente(2));
            // Mostramos el almacén
            System.out.println(almacen);

        } catch (AlmacenException e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método para crear las cajas y añadirlas al mapa
    public static void crearCajas() {
        for (int i = 0; i < 5; i++) {
            almacen.getCajas().put(new Caja(), new ArrayList<>());
        }
    }
}