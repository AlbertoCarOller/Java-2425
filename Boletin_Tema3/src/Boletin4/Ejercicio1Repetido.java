package Boletin4;

public class Ejercicio1Repetido {
    public static void main(String[] args) {
        String cadena = "Frigorifico";
        System.out.println(contarCaracter(cadena, 'f'));
    }

    public static int contarCaracter(String cadena, char caracter) {

        int contador = 0;
        cadena = cadena.toLowerCase();
        caracter = Character.toLowerCase(caracter);

        for (int i = 0; i < cadena.length(); i++) {

            if (cadena.charAt(i) == caracter) {
                contador++;
            }
        }
        return contador;
    }
}
