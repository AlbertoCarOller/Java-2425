package Boletin3.Ejercicio4B3;

import org.xml.sax.SAXException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio4B3 {
    public static void main(String[] args) {
        try {
            System.out.println("Veces que aparece razón: " + contarVecesRazon());
            System.out.println("Veces que aparecen tildes y eñes: " + contarTildes());
            quitarNumeros();
            fiheroLetrasMayusculas();
            palabrasConMasDeN(8);
            frasesConMasDe15Palabras();

        } catch (Ejercicio4B3Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static long contarVecesRazon() throws Ejercicio4B3Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio4B3/quijote.txt");
            try (/*Stream<String> flujo = Files.lines(leerlo)*/
                    BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()))) {
                // Los \\b no tienen en cuenta las tildes, en este caso es un problema utilizarlo, no hará frontera
                Pattern pattern = Pattern.compile("(?i)raz[oó]n");
                /*return flujo.mapToInt(s -> {
                    Matcher matcher = pattern.matcher(s);
                    int numRazon = 0;
                    while (matcher.find()) {
                        numRazon++;
                    }
                    return numRazon;
                }).sum();*/
                // Otra forma de hacer el ejercicio
                String linea;
                long acumulador = 0;
                while ((linea = br.readLine()) != null) {
                    // El .results() devuelve un flujo de Matcher que coinciden
                    acumulador += Pattern.compile("(?i)raz[oó]n").matcher(linea)
                            .results().count();
                }
                return acumulador;
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio4B3Exception(e.getMessage());
        }
    }

    public static int contarTildes() throws Ejercicio4B3Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio4B3/quijote.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()))) {
                String linea;
                int contador = 0;
                Pattern pattern = Pattern.compile("(?i)[áéíóúñ]");
                while ((linea = br.readLine()) != null) {
                    Matcher matcher = pattern.matcher(linea);
                    while (matcher.find()) {
                        contador++;
                    }
                }
                return contador;
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio4B3Exception(e.getMessage());
        }
    }

    public static void quitarNumeros() throws Ejercicio4B3Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio4B3/quijote.txt");
            Path escribirle = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio4B3/quijoteSinNumeros.txt");
            try (Stream<String> flujo = Files.lines(leerlo);
                 PrintWriter pw = new PrintWriter(new FileWriter(escribirle.toFile()))) {
                Pattern pattern = Pattern.compile("(\\p{L}+)\\d+");
                flujo.forEach(l -> {
                    Matcher matcher = pattern.matcher(l);
                    String lineaNueva = l;
                    /* El replaceAll() remplaza lo coincidente con el regex, por lo pasado por
                     * parámetros, hay que tener en cuenta que también se pueden utilizar grupos
                     * de captura, en este caso estamos diciendo que se sustituya por el grupo 1
                     * el cual debe expresarse mediante $, IMPORTANTE TENER CUIDADO CON LO QUE
                     * SE REMPLAZA, SE REMPLAZA CON EL REGEX COMPLETO QUE COINCIDA, en este caso
                     * para no perder la palabra sin el número, solamente cojo la palabra ($1)*/
                    lineaNueva = matcher.replaceAll("$1");
                    // Método sobrecargado .replaceAll()
                    //lineaNueva = matcher.replaceAll(m -> m.group(1));
                    pw.println(lineaNueva);
                });
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio4B3Exception(e.getMessage());
        }
    }

    public static void fiheroLetrasMayusculas() throws Ejercicio4B3Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio4B3/quijote.txt");
            Path escribirle = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio4B3/quijoteMayusculas.txt");
            try (Stream<String> flujo = Files.lines(leerlo);
                 PrintWriter pw = new PrintWriter(new FileWriter(escribirle.toFile()))) {
                flujo.forEach(l -> {
                    String[] partes = l.trim().split("\\s+");
                    //StringBuilder lineaNueva = new StringBuilder();
                    if (partes.length != 0) {
                        /* Pattern.UNICODE_CHARACTER_CLASS acepta las palabras con tilde, el método
                         * sobrecargado acepta una función (expresiones lambda), hace "find" cada
                         * vez que encuentra una coincidencia en la línea y por cada coincidencia
                         * va a reemplazar el grupo 1 que es la primera letra a mayúsculas */
                        Pattern pattern = Pattern.compile("\\b(\\p{L})", Pattern.UNICODE_CHARACTER_CLASS);
                        Matcher matcher = pattern.matcher(l);
                        String lineaNueva = matcher.replaceAll(m -> m.group(1).toUpperCase());
                        for (int i = 0; i < partes.length; i++) {
                            /* Tenemos que comprobar que no esté ese String (parte) vacía o con espacios
                             * ya que al hacer .charAt(0) soltará una excepción ya que se está accediendo
                             * ha un índice que no existe */
                            if (!partes[i].isEmpty() && !partes[i].isBlank()) {
                                /* replaceAll() acepta un regex, aprovechamos esto para indicar que la primera
                                 * letra debe ser sustituida por la misma, pero en mayúsculas, replace no es
                                 * una opción, ya que sustituye todas las coincidencias, en este caso con el
                                 * replaceAll() no, gracias al regex, le indicamos con ^ que debe ser la primera */
                                /*lineaNueva.append(partes[i].replaceAll("^\\p{L}+", String.valueOf(Character
                                        .toUpperCase(partes[i].charAt(0))))).append(" ");*/
                            }
                        }
                        pw.println(lineaNueva);
                    }
                });
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio4B3Exception(e.getMessage());
        }
    }

    public static void palabrasConMasDeN(int numCaracteres) throws Ejercicio4B3Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio4B3/quijoteSinNumeros.txt");
            try (Stream<String> flujo = Files.lines(leerlo)) {
                Pattern pattern = Pattern.compile("\\p{P}*\\p{S}*\\d*(?<PALABRA>\\p{L}+)\\p{P}*\\p{S}*");
                flujo.forEach(l -> {
                    String[] partes = l.split("\\s+");
                    for (int i = 0; i < partes.length; i++) {
                        Matcher matcher = pattern.matcher(partes[i]);
                        if (matcher.matches()) {
                            String palabra = matcher.group("PALABRA");
                            if (palabra.length() > numCaracteres) {
                                System.out.println(palabra);
                            }
                        }
                    }
                });
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio4B3Exception(e.getMessage());
        }
    }

    public static void frasesConMasDe15Palabras() throws Ejercicio4B3Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio4B3/quijoteSinNumeros.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    // En este caso he entendido por frase como cada línea
                    String[] partes = linea.split("\\s+");
                    if (partes.length < 15 && !linea.isBlank() && !linea.isEmpty()) {
                        System.out.println(linea);
                    }
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio4B3Exception(e.getMessage());
        }
    }
}