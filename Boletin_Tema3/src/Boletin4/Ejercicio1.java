package Boletin4;

import utils.MiEntradaSalida;

public class Ejercicio1 {
    public static void main(String[] args) {

        // Solicitamos tanto la cadena como el char
        String palabra = MiEntradaSalida.solicitarCadena("Introduce una cadena");
        char caracter = MiEntradaSalida.solicitarCaracter("Introduce el caracter");

        /* Llamamos al método y le introducimos las variables pero pasándolas a minúsculas para no distinguir
         entre minúsculas y mayúsculas */
        int vecesAparece = contarCaracter(palabra.toLowerCase(), caracter = Character.toLowerCase(caracter));
        System.out.println("En la palabra " + palabra + " aparece " + caracter + " " + vecesAparece + " veces");
    }

    /**
     * Este método va a contar el número de un mismo caracter que va a tener una cadena
     * @param cadena la palabra para contar el número de caracteres
     * @param caracter el caracter que hay que contar en la cadena
     * @return devolvemos el número de caracteres que tiene la cadena
     */
    public static int contarCaracter(String cadena, char caracter) {

        int vecesAparece = 0;

        for (int i = 0; i < cadena.length(); i++) {

            if (cadena.charAt(i) == caracter) {

                vecesAparece++;
            }
        }
        return vecesAparece;
    }
}