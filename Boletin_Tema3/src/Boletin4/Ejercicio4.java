package Boletin4;

public class Ejercicio4 {
    public static void main(String[] args) {

        String cadena = "shybaoxlna";
        String palabraEscondida = "hola";

        System.out.println(encontrarPalabraEscondida(cadena, palabraEscondida));
    }

    public static boolean encontrarPalabraEscondida(String cadena, String palabraEscondida) {

        int indice = 0;

        for (int i = 0; i < palabraEscondida.length(); i++) {

            indice = cadena.indexOf(palabraEscondida.charAt(i), indice);

            if (indice == -1) {

                return false;
            }
        }
        return true;
    }
}