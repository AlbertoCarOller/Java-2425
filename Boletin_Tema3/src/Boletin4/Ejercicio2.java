package Boletin4;

import utils.MiEntradaSalida;

public class Ejercicio2 {
    public static void main(String[] args) {

        String cadena = MiEntradaSalida.solicitarCadena("Introduce una cadena");

        int mayusculas = contarMayusculas(cadena);
        int minusculas = contarMinusculas(cadena);
        int numeros = contarNumeros(cadena);

        System.out.println("La cadena tiene " + mayusculas + " mayúsculas, " + minusculas + " minúsculas y "
                + numeros + " números");
    }

    public static int contarMayusculas(String cadena) {

        int contador = 0;

        for (int i = 0; i < cadena.length(); i++) {

            if (Character.isLetter(cadena.charAt(i))) {
                
                if (Character.isUpperCase(cadena.charAt(i))) {

                    contador++;
                }
            }
        }
        return contador;
    }

    public static int contarMinusculas(String cadena) {

        int contador = 0;

        for (int i = 0; i < cadena.length(); i++) {

            if (Character.isLetter(cadena.charAt(i))) {

                if (Character.isLowerCase(cadena.charAt(i))) {

                    contador++;
                }
            }
        }
        return contador;
    }

    public static int contarNumeros(String cadena) {

        int contador = 0;

        for (int i = 0; i < cadena.length(); i++) {

            if (Character.isDigit(cadena.charAt(i))) {

                contador++;
            }
        }
        return contador;
    }
}