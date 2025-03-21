package Boletin4;

import java.util.Arrays;

public class Ejercicio5 {
    public static void main(String[] args) {

        String frase = "El lenguaje Java es un lenguaje de alto nivel";
        String textoABuscar = "lenguaje";
        String textoAMeter = "churumbel";
        remplazarTexto(frase, textoABuscar, textoAMeter);
        /* remplazarTexto2(frase, textoABuscar, textoAMeter); */
    }

    /**
     * Este método divide las palabras separadas por un espacio y lo guarda en un array,
     * si encuentra la palabra a sustituir en una posición lo sustituye
     *
     * @param frase        es la frase completa
     * @param textoABuscar el texto en la frase que queremos sustituir
     * @param textoAMeter  el texto que queremos que sustituya al texto original
     */
    public static void remplazarTexto(String frase, String textoABuscar, String textoAMeter) {

        String[] frasePorPartes = frase.split(" ");

        for (int i = 0; i < frasePorPartes.length; i++) {

            if (frasePorPartes[i].equals(textoABuscar)) {

                frasePorPartes[i] = textoAMeter;
            }
        }
        System.out.println(Arrays.toString(frasePorPartes));
    }

    // Otra forma de hacerlo pero con 'StringBuilder'
    public static void remplazarTexto2(String frase, String textoABuscar, String textoAMeter) {

        String[] frasePorPartes = frase.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < frasePorPartes.length; i++) {

            if (frasePorPartes[i].equals(textoABuscar)) {
                sb.append(textoAMeter).append(" ");

            } else {
                sb.append(frasePorPartes[i]).append(" ");
            }
        }
        String textoTerminado = sb.toString();
        System.out.println(textoTerminado);
    }
}