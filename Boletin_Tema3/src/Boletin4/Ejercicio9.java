package Boletin4;

public class Ejercicio9 {
    public static void main(String[] args) {

        String cadena = "2025 será un año de 10";
        System.out.println(sumarNumeros(cadena));
    }

    public static int sumarNumeros(String cadena) {

        StringBuilder sb = new StringBuilder();
        int suma = 0;

        for (int i = 0; i < cadena.length(); i++) {

            if (Character.isDigit(cadena.charAt(i))) {

                sb.append(cadena.charAt(i));

            } else if (cadena.charAt(i) == ' ' && sb.length() > 0) {

                String digitos = sb.toString();
                suma += Integer.parseInt(digitos);
                sb.setLength(0);
            }
        }

        if (sb.length() > 0) {

            String digitos = sb.toString();
            suma += Integer.parseInt(digitos);
        }
        return suma;
    }
}