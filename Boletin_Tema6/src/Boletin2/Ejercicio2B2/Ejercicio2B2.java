package Boletin2.Ejercicio2B2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio2B2 {
    public static void main(String[] args) {
        try {
            //System.out.println(contarYBuscarPalabra());
            System.out.println(contarYBuscarPalabraV2());

        } catch (Ejercicio2BD2Exception | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método cuenta el número de palabras que contiene un fichero,
     * también encuentra todas las coincidencias del nombre especificado
     * y muestra en la posición donde se encuentra
     * @return el número de palabras del fichero especificado
     * @throws Ejercicio2BD2Exception
     */
    public static int contarYBuscarPalabra() throws Ejercicio2BD2Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin2/FicheroEJBoletin2.txt");
            Path escribirle = Path.of("Boletin_tema6/src/Boletin2/BuscandoPalabraXXXXX.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribirle.toFile()))) {
                String linea;
                int numeroPalabras = 0;
                int numLinea = 0;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.trim().split(" ");
                    numeroPalabras += partes.length;
                    numLinea++;
                    Pattern pattern = Pattern.compile("Alberto");
                    Matcher matcher = pattern.matcher(linea);
                    /* Mientras find() devuelva true, voy a imprimir el número de línea y donde comienza,
                     * es decir como se menciona en este ejercicio la columna, find() tiene u apuntador
                     * interno por lo que va a poder encontrar donde están, no se reinicia por cada true,
                     * aparte en caso de utilizar matches tendríamos un problema, ya que devuelve true
                     * únicamente si toda la línea coincíde */
                    while (matcher.find()) {
                        pw.println("Está en la línea " + numLinea + " en la columna " + matcher.start());
                    }
                }
                return numeroPalabras;
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio2BD2Exception(e.getMessage());
        }
    }

    /**
     * Este método cuenta el número de palabras que contiene un fichero,
     * también encuentra todas las coincidencias del nombre especificado
     * y muestra en la posición donde se encuentra, es una segunda versión
     *
     * @return el número de palabras que hay en el fichero
     * @throws Ejercicio2BD2Exception
     */
    public static AtomicInteger contarYBuscarPalabraV2() throws Ejercicio2BD2Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin2/FicheroEJBoletin2.txt");
            Path escribirle = Path.of("Boletin_Tema6/src/Boletin2/BuscandoPalabraXXXXX.txt");
            /* El método .lines() devuelve un flujo de String, cada elemento de este flujo
             * estará compuesto por las líneas del fichero */
            try (Stream<String> flujo = Files.lines(leerlo)) {
                Pattern pattern = Pattern.compile("Alberto");
                AtomicInteger linea = new AtomicInteger();
                AtomicInteger numPalabras = new AtomicInteger();
                flujo.forEach(l -> {
                    linea.incrementAndGet();
                    numPalabras.set(numPalabras.get() + l.trim().split(" ").length);
                    Matcher matcher = pattern.matcher(l);
                    while (matcher.find()) {
                        try {
                            /* El método .writeString() escribe directamente en el fichero pasado por parámetros
                             * el contenido pasado por parámetros, en este caso le ponemos también el
                             * 'StandardOpenOption.APPEND' lo que asegura que no se sobrescriba el fichero */
                            Files.writeString(escribirle, "Está en la línea " + linea.get() + " y en la columna "
                                    + matcher.start() + "\n", StandardOpenOption.APPEND);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                return numPalabras;
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio2BD2Exception(e.getMessage());
        }
    }
}