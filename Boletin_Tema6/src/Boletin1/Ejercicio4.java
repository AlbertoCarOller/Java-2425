package Boletin1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Ejercicio4 {
    public static void main(String[] args) {
        // Accedemos al fichero origen .txt mediante Path
        Path pt = Path.of("Boletin_Tema6/salidaEjercicio3.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String l;
            while ((l = br.readLine()) != null && !l.equalsIgnoreCase("fin")) {
                /* Mediante Files.writeString() escribimos el contenido de l en el fichero origen que le pasamos,
                 * es decir pt en este caso, StandardOpenOption.CREATE especifica que debe crearse el fichero
                 * en caso de que no exista y StandardOpenOption.APPEND no borra lo contenido anteriormente
                 * lo añade a lo ya estaba en el fichero*/
                Files.writeString(pt, l + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}