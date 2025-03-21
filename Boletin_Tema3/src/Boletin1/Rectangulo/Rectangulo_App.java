package Boletin1.Rectangulo;

import java.util.Scanner;

public class Rectangulo_App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Creamos el objeto 'rectangulo'
        Rectangulo rectangulo = new Rectangulo();

        // Creamos el boolean para manejar el do while
        boolean error = true;
        do {
            try {
                // Pedimos una altura que le pasamos al objeto 'rectangulo'
                System.out.println("Introduce una altura");
                int altura = sc.nextInt();
                rectangulo.setLongitud(altura);
                error = false;
            } catch (RectanguloException e) {
                System.out.println(e.getMessage());
            }
        } while (error);


        // Pedimos una anchura que pasamos por parámetros al objeto 'rectangulo'
        System.out.println("Introduce una anchura");
        int anchura = sc.nextInt();
        rectangulo.setAncho(anchura);

        // Imprimimos el área llamando al método 'area' del objeto 'rectangulo'
        System.out.println("El área es de " + rectangulo.area());
        // Imprimimos el perímetro llamando al método 'perimetro' del objeto 'rectangulo'
        System.out.println("El perímetro es de " + rectangulo.perimetro());
        // Imprimimos el objeto 'rectangulo'
        System.out.println(rectangulo);
    }
}
