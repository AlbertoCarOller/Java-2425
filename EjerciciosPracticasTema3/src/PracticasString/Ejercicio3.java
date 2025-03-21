package PracticasString;

import utils.MiEntradaSalida;

public class Ejercicio3 {
    public static void main(String[] args) {
        // Solicitamos la cadena
        String cadena = MiEntradaSalida.solicitarCadena("Introduce la cadena");
        // Separamos el String por espacios
        String[] partesCadena = cadena.split(" ");
        // Creamos un StringBuilder
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < partesCadena.length - 1; i++) {

            // Si la cadena es 'calle', 'avenida' o 'plaza' la transforma en mayúscula
            if (partesCadena[i].equalsIgnoreCase("calle") || partesCadena[i]
                    .equalsIgnoreCase("avenida") || partesCadena[i].equalsIgnoreCase("plaza")) {
                partesCadena[i] = partesCadena[i].toUpperCase();
            }

            // Si la siguiente cadena es igual a la actual borramos la posición actual
            if (partesCadena[i].equalsIgnoreCase(partesCadena[i + 1])) {
                partesCadena[i] = null;
            }
        }

        for (int i = 0; i < partesCadena.length; i++) {

            if (partesCadena[i] != null) {
                sb.append(partesCadena[i]).append(" ");
            }
        }

        for (int i = 0; i < sb.length(); i++) {

            if (!Character.isLetterOrDigit(sb.charAt(i)) && sb.charAt(i) != ' ') {
                sb.deleteCharAt(i);
            }
        }
        String cadenaFinal = sb.toString();
        System.out.println(cadenaFinal);
    }
}