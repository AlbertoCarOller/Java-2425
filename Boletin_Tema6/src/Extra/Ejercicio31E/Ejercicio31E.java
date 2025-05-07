package Extra.Ejercicio31E;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio31E {
    public static void main(String[] args) {
        try {
            System.out.println("La palabra bosque ha aparecido " + contarBosque() + " veces");
            System.out.println("Hay " + contarTildes() + " tildes y ñ");
            eliminarFechas();
            pasarMayusculas();
            mostrarPalabrasEntre9Y12Letras();
            mostrarFrasesPorElYMenosDe12();

        } catch (Ejercicio31EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a contar cuántas veces aparece la palabra bosque
     * en mayúsculas, minúsculas, singular o plural
     *
     * @return el número de veces que aparece 'bosque'
     * @throws Ejercicio31EException
     */
    public static AtomicInteger contarBosque() throws Ejercicio31EException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio31E/cuento.txt");
            try (Stream<String> flujo = Files.lines(leerlo)) {
                Pattern pattern = Pattern.compile("(?i)\\b(bosque|bosques)\\b");
                AtomicInteger atomicInteger = new AtomicInteger();
                flujo.forEach(l -> {
                    Matcher matcher = pattern.matcher(l);
                    atomicInteger.set(atomicInteger.get() + (int) matcher.results().count());
                });
                return atomicInteger;
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio31EException(e.getMessage());
        }
    }

    /**
     * Este método va a contar el número de tildes y ñ que hay en el texto
     *
     * @return el número de tildes y ñ
     * @throws Ejercicio31EException
     */
    public static int contarTildes() throws Ejercicio31EException {
        try {
            Path leerlo = Paths.get("Boletin_Tema6/src/Extra/Ejercicio31E/cuento.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()))) {
                String linea;
                Pattern pattern = Pattern.compile("(?i)[áéíóúñ]");
                int contador = 0;
                while ((linea = br.readLine()) != null) {
                    Matcher matcher = pattern.matcher(linea);
                    contador += (int) matcher.results().count();
                }
                return contador;
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio31EException(e.getMessage());
        }
    }

    /**
     * Este método va a eliminar las fechas que se encuentren e el texto original
     * y va a crear un nuevo documento sin fechas
     *
     * @throws Ejercicio31EException
     */
    public static void eliminarFechas() throws Ejercicio31EException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio31E/cuento.txt");
            Path escribirle = Path.of("Boletin_Tema6/src/Extra/Ejercicio31E/cuentoNuevo.txt");
            try (Stream<String> flujo = Files.lines(leerlo)) {
                Files.deleteIfExists(escribirle);
                Files.createFile(escribirle);
                Pattern fecha = Pattern.compile("\\b+((?:0[1-9]|[12][0-9]|3[01])[/-](?:0[1-9]|1[0-2])" +
                        "[/-](?:19\\d{2}|20\\d{2}))\\b*");
                flujo.forEach(s -> {
                    Matcher matcher = fecha.matcher(s);
                    try {
                        Files.writeString(escribirle, matcher.replaceAll(m -> "") + "\n", StandardOpenOption.APPEND);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        } catch (IOException | RuntimeException e) {
            throw new Ejercicio31EException(e.getMessage());
        }
    }

    /**
     * Este método va a pasar a mayúsculas la primera letra de cada palabra
     *
     * @throws Ejercicio31EException
     */
    public static void pasarMayusculas() throws Ejercicio31EException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio31E/cuento.txt");
            Path escribirle = Path.of("Boletin_Tema6/src/Extra/Ejercicio31E/cuentoNuevo2.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribirle.toFile()))) {
                String linea;
                // Pattern.UNICODE_CHARACTER_CLASS -> Acepta tildes
                Pattern pattern = Pattern.compile("\\b(\\p{L})", Pattern.UNICODE_CHARACTER_CLASS);
                while ((linea = br.readLine()) != null) {
                    Matcher matcher = pattern.matcher(linea);
                    pw.println(matcher.replaceAll(m -> m.group(1).toUpperCase()));

                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio31EException(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar las palabras que tengan entre 9 y 12 letras
     *
     * @throws Ejercicio31EException
     */
    public static void mostrarPalabrasEntre9Y12Letras() throws Ejercicio31EException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio31E/cuento.txt");
            Pattern pattern = Pattern.compile("\\b(\\p{L}{9,12})\\b");
            try (Stream<String> flujo = Files.lines(leerlo)) {
                flujo.forEach(l -> {
                    Matcher matcher = pattern.matcher(l);
                    matcher.results().forEach(m -> {
                        System.out.println(matcher.group(1));
                    });
                });
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio31EException(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar las frases que empiecen por 'El' y que
     * tenga menos de 12 letras
     *
     * @throws Ejercicio31EException
     */
    public static void mostrarFrasesPorElYMenosDe12() throws Ejercicio31EException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio31E/cuento.txt");
            try (Stream<String> flujo = Files.lines(leerlo)) {
                Pattern pattern = Pattern.compile("\\b\\p{L}+\\b", Pattern.UNICODE_CHARACTER_CLASS);
                Pattern pattern1 = Pattern.compile("^El");
                flujo.forEach(l -> {
                    Matcher matcher = pattern.matcher(l);
                    Matcher matcher1 = pattern1.matcher(l);
                    if (matcher.results().count() < 12) {
                        if (matcher1.find()) {
                            System.out.println(l);
                        }
                    }
                });
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio31EException(e.getMessage());
        }
    }
}