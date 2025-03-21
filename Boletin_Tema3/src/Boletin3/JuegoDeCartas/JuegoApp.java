package Boletin3.JuegoDeCartas;

import utils.MiEntradaSalida;

public class JuegoApp {
    public static void main(String[] args) {

        // Creamos el objeto juego
        Juego juego = new Juego();

        // Creamos la baraja
        juego.crearCartasEnBaraja();

        // Mostramos la baraja que se ha creado
        System.out.println("Baraja antes de barajar");
        System.out.println(juego);

        // Bajaramos la baraja
        juego.barajar();

        // Volvemos a mostrar la baraja una vez ya barajada
        System.out.println("Baraja ya una vez barajada");
        System.out.println(juego);

        boolean error;
        int respuesta;

        do {
            do {
                error = true;
                // Repartimos las cartas entre los jugadores
                try {
                    int jugadores = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de jugadores");
                    int cartasARepartir = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de cartas");
                    juego.repartir(jugadores, cartasARepartir);
                    error = false;

                } catch (JuegoExcepcion e) {
                    System.out.println(e.getMessage());
                }
            } while (error);

            // Mostramos las cartas que quedan en el mazo
            juego.cartasRestantes();

            respuesta = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 si no quieres repartir más cartas");
        } while (respuesta != 1);
    }
}