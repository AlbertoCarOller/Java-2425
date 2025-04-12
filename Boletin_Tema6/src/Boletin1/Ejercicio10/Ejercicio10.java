package Boletin1.Ejercicio10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio10 {
    public static void main(String[] args) {
        try {
            crearFicheroValido();

        } catch (Ejercicio10Exception | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Hacemos un método que va a crear ficheros con los nombres especificados
     * en el fichero a mirar
     *
     * @throws Ejercicio10Exception
     */
    public static void crearFicheroValido() throws Ejercicio10Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin1/Ejercicio10/FicherosParaCrear.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()))) {
                Set<String> archivos = new HashSet<>();
                String arhivo;
                while ((arhivo = br.readLine()) != null) {
                    String[] s = arhivo.split(" ");
                    archivos.add(s[s.length - 1]);
                }
                archivos.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^[a-zA-Z]{3,}\\.[a-z]{3}");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(s -> {
                    try {
                        Path path = Path.of("Boletin_Tema6/src/Boletin1/Ejercicio10/" + s);
                        if (!Files.exists(path)) {
                            System.out.println("Se ha creado el fichero " + s);
                            Files.createFile(path);

                        } else {
                            System.out.println("El fichero " + s + " ya existe");
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                archivos.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^[a-zA-Z]{3,}\\.[a-z]{3}");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return false;
                    }
                    return true;
                }).map(s -> "No se ha podido crear el fichero " + s + "\n").forEach(System.out::println);

            } catch (IOException e) {
                throw new Ejercicio10Exception(e.getMessage());
            }

        } catch (InvalidPathException e) {
            throw new RuntimeException(e);
        }
    }
}