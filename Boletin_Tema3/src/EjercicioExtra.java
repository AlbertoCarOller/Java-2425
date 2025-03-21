import java.util.Scanner;

public class EjercicioExtra {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder frase = new StringBuilder();
    static String palabra;

    public static void main(String[] args) {

        fraseCompleta();
    }

    public static void fraseCompleta() {

        do {
            System.out.println("Introduce una palabra");
            palabra = sc.nextLine();

            if (!"fin".equals(palabra)) {
                frase.append(" ").append(palabra);
                System.out.println(frase);

            } else {
                System.out.println("Has introducido fin");
            }
        } while (!"fin".equals(palabra));
    }
}