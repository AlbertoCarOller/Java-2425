package Extra.Ejercicio21E;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio21E {
    public static void main(String[] args) {
        try {
            crearDirectorioYFicheroConFraseValidar("Directorio_1", "Fichero_1.txt", "Hola buenas tardes.");

        } catch (Ejercicio21EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a crear un directorio en caso de que no exista ya, un fichero en caso de que
     * no exista, con contenido dentro del fichero y el nombre del mismo. También se listarán los
     * ficheros que pertenezcan a un directorio por orden alfabético
     *
     * @param nombreDirectorio
     * @param nombre
     * @param frase
     * @throws Ejercicio21EException
     */
    public static void crearDirectorioYFicheroConFraseValidar(String nombreDirectorio, String nombre, String frase)
            throws Ejercicio21EException {
        try {
            Path directorio = Path.of("Boletin_Tema6/src/Extra/Ejercicio21E/" + nombreDirectorio);
            Path fichero = Path.of("Boletin_Tema6/src/Extra/Ejercicio21E/" + nombreDirectorio + "/" + nombre);
            Matcher matcher;
            if (!Files.exists(directorio)) {
                Pattern pattern = Pattern.compile("^[A-Z][a-z0-9_]+$");
                matcher = pattern.matcher(nombreDirectorio);
                if (!matcher.matches()) {
                    throw new Ejercicio21EException("El directorio no tiene un nombre válido");
                }
                Files.createDirectory(directorio);
            }
            if (!Files.exists(fichero)) {
                /* En el regex cuando se hace esto (?<...>...) le ponemos a ese grupo de captura el nombre que va entre
                 * el operador diamante, entonces después al llamar a ese grupo de captura, lo puedes llamar directamente
                 * con el nombre en vez de con el número que corresponde, por ejemplo así matcher.group("NOMBRE") en vez
                 * de así matcher.group(1) */
                Pattern pattern = Pattern.compile("^(?<NOMBRE>[a-zA-Z\\p{N}\\p{P}]+)\\.txt$");
                matcher = pattern.matcher(nombre);
                if (!matcher.matches()) {
                    throw new Ejercicio21EException("El fichero no tiene un nombre válido");
                }
                Files.createFile(fichero);
                /* Hacemos el try con el PrintWriter aquí porque llegados a este punto existe tanto el directorio
                 * como el fichero, en caso de hacerlo sin haberlo creado previamente, al pasarle fichero
                 * saltaría una excepción porque aún no estaría creada */
                try (PrintWriter pw = new PrintWriter(new FileWriter(fichero.toFile()))) {
                    pw.print(frase + "\n");
                    pw.print(matcher.group("NOMBRE"));
                }
            }
            Path directorioAMirar = Path.of("Boletin_Tema6/src/Extra/Ejercicio12E");
            try (Stream<Path> ficheros = Files.list(directorioAMirar)) {
                ficheros.filter(p -> {
                    /* En el regex cuando haces un [^...] significa que puede haber cualquier caracter que no sea lo
                     * que tenga dentro. Cuando se hace (?!...) quiere decir que no puede existir una secuencia como
                     * la que está dentro de los paréntesis */
                    Pattern pattern = Pattern.compile("^[A-Za-z][^\\p{P}\\p{S}]+\\.[a-z]{3,}$", Pattern.UNICODE_CHARACTER_CLASS);
                    Matcher matcher1 = pattern.matcher(p.toFile().getName());
                    if (matcher1.matches()) {
                        if (Files.isRegularFile(p)) {
                            return true;
                        }
                        return false;
                    }
                    return false;
                }).sorted(Comparator.comparing(p -> p.toFile().getName())).forEach(p -> System.out.println(p.toFile().getName()));
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio21EException(e.getMessage());
        }
    }
}