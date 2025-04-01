package Boletin1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("Boletin_Tema6/Ejercicio1.txt"))) {
            StringBuilder sb = new StringBuilder();
            String l;
            while ((l = br.readLine()) != null) {
                sb.append(l);
            }
            System.out.println(sb);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
