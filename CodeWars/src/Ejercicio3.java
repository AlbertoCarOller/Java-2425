import java.util.Arrays;

public class Ejercicio3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(stringToArray("Hola Adios ¿Qué tal?")));
    }

    // Hacemos un método para dividir los String a Array de Strings
    public static String[] stringToArray(String s) {

        String[] strings = s.split(" ");

        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
        }
        return strings;
    }
}