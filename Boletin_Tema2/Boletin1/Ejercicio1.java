package Boletin1;

public class Ejercicio1 {
    public static void main(String[] args) {
        int[] array = {1, 4, 5, 7, 8, 16, 23, 12};

        int suma = sumaArray(array);

        System.out.println("La suma de las posiciones pares es " + suma);
    }

    private static int sumaArray(int[] array) {
        int suma = 0;
        for (int i = 0; i < array.length; i++) {

            if (i % 2 == 0) {

                suma += array[i];
            }
        }
        return suma;
    }
}
