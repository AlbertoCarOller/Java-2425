package Boletin4;

public class Ejercicio6 {
    public static void main(String[] args) {

        String cadena = "Abaco";
        int vocalesDistintas = contarVocalesDiferentes(cadena);
        System.out.println(vocalesDistintas);
    }

    public static int contarVocalesDiferentes(String cadena) {

        String cadenaSinDistinciones = cadena.toLowerCase();
        int indice;
        String vocales = "oeiau";
        int numeroVocalesDistintas = 0;

        for (int i = 0; i < vocales.length(); i++) {

            indice = cadenaSinDistinciones.indexOf(vocales.charAt(i));

            if (indice != -1) {

                numeroVocalesDistintas++;
            }
        }
        return numeroVocalesDistintas;
    }
}