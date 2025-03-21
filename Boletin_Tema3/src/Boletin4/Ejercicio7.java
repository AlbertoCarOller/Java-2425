package Boletin4;

public class Ejercicio7 {
    public static void main(String[] args) {

        String frase = "curso de programacion";
        StringBuilder fraseModificada = consonantesVocales(frase);
        System.out.println(fraseModificada.toString());
    }

    public static StringBuilder consonantesVocales(String cadena) {

        cadena = cadena.toLowerCase();
        StringBuilder fraseFinal = new StringBuilder();
        StringBuilder cadenaConsonantes = new StringBuilder();
        StringBuilder cadenaVocales = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {

            if (cadena.charAt(i) == 'a' || cadena.charAt(i) == 'e' || cadena.charAt(i) == 'i' ||
                    cadena.charAt(i) == 'o' || cadena.charAt(i) == 'u') {

                cadenaVocales.append(cadena.charAt(i));

            } else if (cadena.charAt(i) != ' '){

                cadenaConsonantes.append(cadena.charAt(i));
            }
        }
        fraseFinal.append(cadenaConsonantes).append(cadenaVocales);
        return  fraseFinal;
    }
}