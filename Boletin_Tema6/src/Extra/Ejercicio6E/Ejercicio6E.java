package Extra.Ejercicio6E;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio6E {
    public static void main(String[] args) {
        try {
            mostrarFicherosPatron("Ejercicio6E");

        } catch (Ejercicio6EException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a recorrer los ficheros dentro de la carpeta y mostrar su peso,
     * solo se van a mostrar los ficheros válidos
     *
     * @param nombreDirectorio
     * @throws Ejercicio6EException
     */
    public static void mostrarFicherosPatron(String nombreDirectorio) throws Ejercicio6EException {
        try {
            Path directorio = Path.of("Boletin_Tema6/src/Extra/" + nombreDirectorio);
            try (Stream<Path> flujo = Files.list(directorio)) {
                flujo.filter(p -> {
                    Pattern pattern = Pattern.compile("^[A-Z]{3}-[0-9]{4}\\.[a-z]{3,}$");
                    Matcher matcher = pattern.matcher(p.toFile().getName());
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(p -> {
                    if (Files.isRegularFile(p)) {
                        try {
                            System.out.println("Nombre: " + p.getFileName() + "Peso: " + (double) Files.size(p));

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println(p.getFileName() + " no es un fichero");
                    }
                });
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio6EException(e.getMessage());
        }
    }
}