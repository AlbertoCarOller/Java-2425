package PracticasString;

import utils.MiEntradaSalida;

public class Ejercicio4 {
    public static void main(String[] args) {
        String contrasena = MiEntradaSalida.solicitarCadena("Introduce la contraseña");

        if (comprobarContrasena(contrasena)) {
            System.out.println("La contraseña se ha creado correctamente");

        } else {
            System.out.println("No cumple con los requisitos");
        }
    }

    // Hacemos un método para comprobar que la contraseña cumpla los requisitos
    public static boolean comprobarContrasena(String contrasena) {

        if (!Character.isUpperCase(contrasena.charAt(0))) {
            return false;
        }

        if (!Character.isDigit(contrasena.charAt(contrasena.length() - 1))) {
            return false;
        }

        int indice = contrasena.indexOf(" ");

        if (indice != -1) {
            return false;
        }

        for (int i = 0; i < contrasena.length() - 1; i++) {

            if (contrasena.charAt(i) == contrasena.charAt(i + 1)) {
                return false;
            }
        }

        if (contrasena.length() != 8) {
            return false;
        }
        return true;
    }
}