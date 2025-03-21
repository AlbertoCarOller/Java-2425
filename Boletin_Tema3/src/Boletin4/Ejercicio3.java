package Boletin4;

public class Ejercicio3 {
    public static void main(String[] args) {

        String palabra = "";
        System.out.println(esPalindromo("anilino"));
        System.out.println(esPalindromo2("anilino"));
    }

    public static boolean esPalindromo(String cadena) {

        for (int i = 0; i < cadena.length() / 2; i++) {

            if (cadena.charAt(i) != cadena.charAt(cadena.length() - i - 1)) {

                return false;
            }
        }
        return true;
    }

    public static boolean esPalindromo2(String cadena) {

        String palabraAlReves = new StringBuilder(cadena).reverse().toString();

        return cadena.equals(palabraAlReves);
    }
}