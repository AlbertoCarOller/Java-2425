package Boletin2.Ejercicio3B2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio3B2 {
    public static void main(String[] args) {
        try {
            System.out.println(validacionContenidoFichero());

        } catch (Ejercicio3BD2Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método verifica las líneas del fichero y en caso de que no cumplan
     * con la validéz que debería tener se informará de la línea o líneas donde
     * ocurre el error o los errores
     *
     * @return
     * @throws Ejercicio3BD2Exception
     */
    public static String validacionContenidoFichero() throws Ejercicio3BD2Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin2/Ejercicio3B2/FicheroInfoPersonas.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()))) {
                String linea;
                Pattern pattern = Pattern.compile("^(\\p{Lu}\\p{Ll}+|\\p{Lu}\\p{Ll}+ \\p{Lu}\\p{Ll}+)" +
                        " \\p{Lu}\\p{Ll}+ \\p{Lu}\\p{Ll}+ [1-9][0-9]$");
                Matcher matcher;
                StringBuilder sb = new StringBuilder(); // El mensaje que se le mostrará por pantalla al usuario
                boolean alMenosUn = false; // Informará de si hay al menos un error o no hay errores
                int nuimLinea = 0; // Un contador de las líneas
                while ((linea = br.readLine()) != null) {
                    nuimLinea++;
                    matcher = pattern.matcher(linea);
                    if (!matcher.matches()) {
                        alMenosUn = true;
                        sb.append("Hay un error en la línea ").append(nuimLinea).append("\n");
                    }
                }
                if (!alMenosUn) {
                    sb.append("No hay errores en el fichero");
                }
                return sb.toString();
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio3BD2Exception(e.getMessage());
        }
    }
}