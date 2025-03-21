package Boletin4;

import utils.MiEntradaSalida;

public class Ejercicio11 {
    public static void main(String[] args) {
        buscarLetra("Rinoceronte");
    }

    public static void buscarLetra(String cadena) {

        cadena = cadena.toLowerCase();
        int indice;
        int numFallos = 0;
        int numAciertos = 0;
        char[] palabras = new char[cadena.length()];

        do {
            boolean palabraRepetida = true;
            boolean palabraAcertada = false;
            char letra = MiEntradaSalida.solicitarCaracter("Introduce la letra");
            indice = cadena.indexOf(letra);

            if (indice == -1) {
                System.out.println("No está en la palabra");
                numFallos++;

            } else {

                for (int i = 0; i < palabras.length; i++) {

                    if (cadena.charAt(i) == letra && palabras[i] == 0) {
                        palabras[i] = letra;
                        numAciertos++;
                        palabraAcertada = true;
                        palabraRepetida = false;
                    }
                }

                if (palabraRepetida) {
                    System.out.println("Palabra repetida");
                }

                if (palabraAcertada) {
                    System.out.println("Palabra correcta");
                }
            }

            if (numFallos == 7) {
                System.out.println("Has perdido");

            } else if (numAciertos == cadena.length()) {
                System.out.println("Has ganado");
            }

        } while (numFallos != 7 && numAciertos < cadena.length());
    }
}