package Boletin1.Ejercicio8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio8 {
    public static void main(String[] args) {
        try {
            validarNombreFichero("rubén garcía meriñaque 20");

        } catch (Ejercicio8Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método pasado un nombre, en este caso el nombre de un fichero se comprueba
     * si el nombre de este es válido, en caso de que no, se saltará excepción
     *
     * @param nombreFichero
     */
    public static void validarNombreFichero(String nombreFichero) throws Ejercicio8Exception {
        /* Esta expresión comprueba que la cadena tenga 3 palabras de la a la z que tenga como mínimo 2 caracteres
         * no tiene máximo y por último comprueba que haya dos números, el primero del 1 al 9 y el segundo del
         * 0 al 9. 'Pattern' almacena y compila la expresión regular. El 'Pattern.UNICODE_CHARACTER_CLASS'
         * permite la utilización de caracteres de todos los tipos*/
        Pattern validacion = Pattern.compile("^\\p{L}{2,}\\s\\p{L}{2,}\\s\\p{L}{2,}\\s[1-9][0-9]$",
                Pattern.UNICODE_CHARACTER_CLASS);
        // Asignamos a la cadena la validación mediante 'Matcher'
        Matcher matcher = validacion.matcher(nombreFichero);
        // Si el nombre del fichero no es válido se saltará excepción
        if (!matcher.matches()) {
            throw new Ejercicio8Exception("El nombre del fichero no es válido");
        }
    }
}