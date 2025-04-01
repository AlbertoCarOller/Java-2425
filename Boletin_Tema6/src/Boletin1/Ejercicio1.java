package Boletin1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("Boletin_Tema6/Ejercicio1.txt"))) {
            int contador = 0;
            while (br.readLine() != null) {
                contador++;
            }
            System.out.println("El fichero tiene " + contador + " líneas");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}