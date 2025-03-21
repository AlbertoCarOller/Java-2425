package PracticasString;

import utils.MiEntradaSalida;

public class Ejercicio1 {
    public static void main(String[] args) {

        String cadena = MiEntradaSalida.solicitarCadena("Introduce una frase");

        // Si la cadena no contiene espacios en blanco
        if (!cadena.isBlank()) {
            // Eliminamos los espacios al princio y al final
            cadena = cadena.trim();
            System.out.println(cadena);

        } else {
            System.out.println("La cadena solo tiene espacios en blanco");
        }

        // Si la cadena comienza por 'hola' indiferentemente de que esté en mayúsculas o minúsculas entra
        if (cadena.toLowerCase().startsWith("hola")) {
            System.out.println("La cadena comienza por 'Hola'");
        }

        // Transformo la cadena en minúscula
        cadena = cadena.toLowerCase();
        // Remplaza las a por espacios
        cadena = cadena.replace('a', ' ');
        System.out.println(cadena);

        String[] partesCadena = cadena.split(" ");

        for (int i = 0; i < partesCadena.length; i++) {
            int contadorPalabras = 0;

            for (int j = 0; j < partesCadena[i].length(); j++) {
                contadorPalabras++;
            }

            System.out.println("Hay " + contadorPalabras + "palabras, en la parte número " + i);
        }
    }
}