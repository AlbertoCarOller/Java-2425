package Boletin1;

import java.util.Arrays;

public class Ejercicio10 {
    public static void main(String[] args) {

        int[] array = {4, 5, 9, 1, 9, 8};

        // Llamo al método
        int[] arrayLimpia = eliminarRepetidos(array);
        System.out.println(Arrays.toString(arrayLimpia));
    }

    // Método que elimina los elementos repetidos en un array
    private static int[] eliminarRepetidos(int[] array) {

        // Creamos un tamaño para el 'arrayLimpio'
        int tamano = array.length;

        // Le asignamos el tamaño
        int[] arrayLimpio = new int[tamano];

        // Creamos un índice para saber el tamaño que tendrá el 'arrayNuevo'
        int indice = 0;

        // Recorremos el array original
        for (int i = 0; i < array.length; i++) {

            // Si se repite es falso se guardará el número en 'arrayLimpio'
            boolean seRepite = false;

            // Recorremos 'arrayLimpio' en busca de repetidos, como 'seRepite' es falso, no entrará la primera vez
            for (int j = 0; j < indice; j++) {

                /* Si el valor del array original en la posición i
                 es igual a la del 'arrayLimpio' en la posición j, 'seRepite'
                 pasa a ser verdadero, entonces deja de comparar
                 con que solo se repita una vez y entonces
                 no se guardaría en 'arrayLimpio' */
                if (array[i] == arrayLimpio[j]) {

                    seRepite = true;
                    break;
                }
            }

            /* Si no se repite, es decir que no ha entrado
             en el if anterior, en 'arrayLimpio' se guarda
             el valor del array original en la posición i
             y se suma 1 al índice, para darle una posición
             más al 'arrayNuevo' que ocupará el valor no repetido */
            if (!seRepite) {

                arrayLimpio[indice] = array[i];
                indice++;
            }
        }

        // Le asignamos el tamaño del índice al 'arrayNuevo', en este guardaremos los números no repetidos
        int[] arrayNuevo = new int[indice];

        /* Recorremos tantas veces como valor de índice sea
         y le asignamos los valores de 'arrayLimpio' a 'arrayNuevo' */
        for (int k = 0; k < indice; k++) {

            arrayNuevo[k] = arrayLimpio[k];
        }
        return arrayNuevo;
    }
}