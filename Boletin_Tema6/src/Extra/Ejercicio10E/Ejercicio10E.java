package Extra.Ejercicio10E;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio10E {
    public static void main(String[] args) {
        try {
            crearDirectoriosYFicheros();

        } catch (Ejercicio10EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a crear directorios y ficheros, los directorios se crean a partir del nombre
     * dentro del fichero especificado y los ficheros se van a crear dentro de las carpetas creadas anteriormente
     * donde corresponda
     *
     * @throws Ejercicio10EException
     */
    public static void crearDirectoriosYFicheros() throws Ejercicio10EException {
        try {
            Path ficheroLeer = Path.of("Boletin_Tema6/src/Extra/Ejercicio10E/FicherosYDirectorios.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(ficheroLeer.toFile()))) {
                Set<String> ficherosDirectorios = new HashSet<>();
                String ficheroYDirectorio;
                while ((ficheroYDirectorio = br.readLine()) != null) {
                    ficherosDirectorios.add(ficheroYDirectorio);
                }
                ficherosDirectorios.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^\\p{Lu}\\p{Ll}{2,} \\p{Lu}\\p{Ll}{2,} \\p{Lu}\\p{Ll}{2,}" +
                            " (0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19[0-9]{2}|20([01][0-9]|2[0-5]))");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(s -> {
                    String[] a = s.split(" ");
                    Path directorio = Path.of("Boletin_Tema6/src/Extra/Ejercicio10E/" + a[0] + a[1] + a[2]);
                    if (!Files.exists(directorio)) {
                        try {
                            Files.createDirectory(directorio);
                            Path fichero = Path.of(directorio + "/" + a[a.length - 1]);
                            if (!Files.exists(fichero)) {
                                Files.createFile(fichero);
                            }

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }

        } catch (IOException | RuntimeException e) {
            throw new Ejercicio10EException(e.getMessage());
        }
    }
}