package Boletin4;

public class Ejercicio8 {
    public static void main(String[] args) {
        String cadena = "    Hola    buenas tardes";
        System.out.println(contarPalabras(cadena));
    }

    public static int contarPalabras(String cadena) {

        String[] cadenaPorPartes = cadena.split(" ");

        int numPalabras = cadenaPorPartes.length;

        for (int i = 0; i < cadenaPorPartes.length; i++) {

            if (cadenaPorPartes[i].isBlank()) {

                numPalabras--;
            }
        }
        return numPalabras;
    }
}