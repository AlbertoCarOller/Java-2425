package Extra.Ejercicio4E;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio4E {
    public static void main(String[] args) {
        try {
            validarNombreArchivos();

        } catch (Ejercicio4EExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método valida los nombres de los archivos dentro de un fichero,
     * en caso de que sean válidos pasan a un nuevo fichero
     * @throws Ejercicio4EExcepcion
     */
    public static void validarNombreArchivos() throws Ejercicio4EExcepcion {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio4E/NombreArchivo.txt");
            Path escribir = Files.createFile(Path.of("Boletin_Tema6/src/Extra/Ejercicio4E/ArchivosValidos.txt"));
            try (BufferedReader bf = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribir.toFile()))) {
                Set<String> archivos = new HashSet<>();
                String nombreArchivo;
                while ((nombreArchivo = bf.readLine()) != null) {
                    archivos.add(nombreArchivo);
                }
                archivos.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_-]+(\\.txt|\\.java|\\.pdf)$");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(pw::println);
            }

        } catch (IOException | InvalidPathException e) {
            throw new Ejercicio4EExcepcion(e.getMessage());
        }
    }
}