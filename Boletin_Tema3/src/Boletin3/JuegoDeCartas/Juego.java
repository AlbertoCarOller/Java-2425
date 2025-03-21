package Boletin3.JuegoDeCartas;

import java.util.Arrays;

public class Juego {

    // Atributos
    private Carta[] baraja;
    // Creo el array con palos
    private static String[] palos = new String[]{"Oro", "Copa", "Espada", "Basto"};
    // Creo el array de números por palo
    private static String[] numeros = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    // El número de cartas del mazo / baraja
    private int cartasMazo;

    // Hacemos el constructor
    public Juego() {
        // Inicializamos la baraja con 48 posiciones, 12 cartas por palo (4 palos)
        baraja = new Carta[palos.length * numeros.length];
        /* Inicializamos 'cartasMazo' aquí, para que no afecte al restar el número de cartas en el mazo de otros
         objetos 'Juego', por eso no lo pongo static, para que al reducirlo no afecte a otros objetos del mismo tipo */
        cartasMazo = 48;
    }

    // Hacemos un método para recorrer la baraja y crear las cartas
    public void crearCartasEnBaraja() {

        for (int i = 0; i < palos.length; i++) {

            for (int j = 0; j < numeros.length; j++) {

                // Creamos una carta en la posición correspondiente
                baraja[i * numeros.length + j] = new Carta(palos[i], numeros[j]);
            }
        }
    }

    // Hacemos el método para imprimir la baraja en el estado actual
    @Override
    public String toString() {
        return "baraja=" + Arrays.toString(baraja) +
                '}';
    }

    // Hacemos un método para desordenar las cartas de la baraja
    public void barajar() {

        //Creamos un nuevo array donde guardar las cartas de forma desordenada
        Carta[] barajaDesordenada = new Carta[baraja.length];

        for (int i = 0; i < baraja.length; i++) {

            int posicion;

            // Vamos a generar posiciones aleatorias hasta encontrar una posición vacía
            do {
                // Generamos números aleatorios del 0 al 47 para generar posiciones aleatorias donde colocar las cartas
                posicion = (int) (Math.random() * 48);

            } while (barajaDesordenada[posicion] != null);

            // Una vez que hemos encontrado una posición vacía le colocamos una carta del array original
            barajaDesordenada[posicion] = baraja[i];
        }
        // Le damos a la baraja original los valores de la nueva baraja una vez ya barajada
        baraja = barajaDesordenada;
    }

    // Hacemos un método para repartir las cartas a los jugadores
    public void repartir(int numJugadores, int numCartas) throws JuegoExcepcion {

        int cartasPico = numCartas % numJugadores;

        // Comprabamos que haya cartas exactas para todos los jugadores
        if (cartasPico != 0 || cartasMazo == 0 || numCartas > cartasMazo) {

            throw new JuegoExcepcion("No hay cartas suficientes para cada jugador");
        }

        // Calculamos y mostramos la cantidad de cartas que se da a cada jugador
        int cartasACadaUno = numCartas / numJugadores;
        System.out.println("Se han repartido " + cartasACadaUno + " cartas a cada jugador");
        cartasMazo -= numCartas;

        /*
        if (numCartas * numJugadores > baraja.length) {

            throw new JuegoExcepcion("No hay cartas suficientes");
        }

        // Otra solución
            for (int i = 0; i < numJugadores; i++) {

                for (int j = 0; j < numCartas; j++) {

                    System.out.println("Le damos a jugador " + i + " " + baraja[i * numJugadores + j]);
                }
            }

            //Copiamos con el método de 'Arrays' la baraja desde un rango hasta otro
            this.baraja = Arrays.copyOfRange(this.baraja, numJugadores *numCartas, this.baraja.length); */
    }

    // Hacemos un método para mostrar las cartas que quedan en el mazo
    public void cartasRestantes() {

        /* Siguiendo la otra solución habría que mostrar this.barajara.legth,
         ya que se modifica el tamaño del array original */

        System.out.println("Quedan " + cartasMazo + " en el mazo");
    }
}