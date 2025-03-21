package Boletin4;

public class Ejercicio10 {
    public static void main(String[] args) {

        String enlace = "http://www.direccion.com";
        int error = formatoEnlaces(enlace);

        if (error == -1) {
            System.out.println("El formato del enlace es inválido");

        } else {
            System.out.println("El formato del enlace es correcto");
        }
    }

    public static int formatoEnlaces(String enlace) {

        String[] partesEnlace = enlace.split("\\.");

        if (partesEnlace.length != 3) {
            return -1;
        }

        if (!partesEnlace[0].equals("http://www")) {
            return -1;
        }

        for (int i = 0; i < partesEnlace[1].length(); i++) {

            if (partesEnlace[1].charAt(i) == ' ') {
                return -1;
            }
        }

        if (Character.isDigit(partesEnlace[1].charAt(0))) {
            return -1;
        }

        if (!partesEnlace[2].equals("es") && !partesEnlace[2].equals("com")) {
            return -1;
        }
        return 1;
    }
}