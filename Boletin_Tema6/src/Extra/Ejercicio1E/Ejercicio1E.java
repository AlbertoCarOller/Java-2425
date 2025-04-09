package Extra.Ejercicio1E;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio1E {
    public static void main(String[] args) {
        try {
            validarCorreo("Correos.txt");

        } catch (Ejercicio1EExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Validamos los correos que contiene el documento 'Correos.txt' los que cumplen la
     * validación pasarán a estar en el fichero nuevo 'CorreosValidos.txt'
     *
     * @param nombreFichero
     * @throws Ejercicio1EExcepcion
     */
    public static void validarCorreo(String nombreFichero) throws Ejercicio1EExcepcion {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio1E/" + nombreFichero);
            Path escribirle = Files.createFile(Path.of("Boletin_Tema6/src/Extra/Ejercicio1E/CorreosValidos.txt"));
            try (BufferedReader bf = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribirle.toFile()))) {
                Set<String> correos = new HashSet<>();
                String correo;
                while ((correo = bf.readLine()) != null) {
                    String[] s = correo.split(" ");
                    correo = s[s.length - 1];
                    correos.add(correo);
                }
                correos.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^[a-z][a-z0-9.]+@(gmail\\.com|hotmail\\.com)$");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(pw::println);
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio1EExcepcion(e.getMessage());
        }
    }
}