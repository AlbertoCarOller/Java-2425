package PracticasString;

import utils.MiEntradaSalida;

public class Ejercicio2 {
    public static void main(String[] args) {
        String cadena = MiEntradaSalida.solicitarCadena("Introduce la cadena");
        /*
        String cadena2 = cadena.replaceAll("\\s+", " ");
        System.out.println(cadena2); */
        // Separamos la cadena por espacios
        String[] partesCadena = cadena.split(" ");
        // Creamos un StringBuilder para poder adjuntar y guardar las partes en las que hemos separado la cadena
        StringBuilder sb = new StringBuilder();

        // Recorremos las partes de la cadena
        for (int i = 0; i < partesCadena.length; i++) {

            // Transformamos cada parte de la cadena a una cadena sin espacios a los lados
            partesCadena[i] = partesCadena[i].trim();

            // Si esa parte de la cadena contiene espacios en blanco no entra
            if (!partesCadena[i].isBlank()) {
                char c = Character.toUpperCase(partesCadena[i].charAt(0));
                partesCadena[i] = c + partesCadena[i].substring(1);
                // Añade esa parte de la cadena con espacio al final, para separala de cada palabra
                sb.append(partesCadena[i]).append(" ");
            }
        }
        /* Transformamos el StringBuilder a String, le hacemos un trim para quitar el último espacio
         en blanco de la última palabra */
        String cadenaSinEspacios = sb.toString();
        cadenaSinEspacios = cadenaSinEspacios.trim();

        // Si no hay un punto al final de la frase le colocamos uno al final
        if (cadenaSinEspacios.charAt(cadenaSinEspacios.length() - 1) != '.') {
            cadenaSinEspacios = cadenaSinEspacios + ".";
        }
        System.out.println(cadenaSinEspacios);
    }
}