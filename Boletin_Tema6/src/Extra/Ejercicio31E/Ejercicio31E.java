package Extra.Ejercicio31E;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio31E {
    public static void main(String[] args) {
        try {
            System.out.println("La palabra bosque ha aparecido " + contarBosque() + " veces");
            System.out.println("Hay " + contarTildes() + " tildes y ñ");

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
}