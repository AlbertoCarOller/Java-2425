package Boletin2.Ejercicio6B2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio6B2 {
    public static void main(String[] args) {
        try {
            crearCursosYAlumnos();

        } catch (Ejercicio6B2Excepcion e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a intentar crear los directorios que corresponde a
     * los cursos y en su interior los ficheros, que corresponde a los nombres
     *
     * @throws Ejercicio6B2Excepcion
     */
    public static void crearCursosYAlumnos() throws Ejercicio6B2Excepcion {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin2/Ejercicio6B2/ficheroAlumnosCursos.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()))) {
                List<String> lineas = new ArrayList<>();
                String linea;
                Pattern pattern = Pattern.compile("^(?<APELLIDO1>\\p{Lu}\\p{Ll}+)(?<APELLIDO2>\\p{Lu}\\p{Ll}+)" +
                        "(?<NOMBRE>\\p{Lu}\\p{Ll}+) (?<CURSO>[1-4]º[A-Z]{3,})$");
                while ((linea = br.readLine()) != null) {
                    lineas.add(linea);
                }
                lineas.stream().map(pattern::matcher).filter(Matcher::matches).forEach(m -> {
                    try {
                        /* .createDirectories() crea directamente todos los directorios que hay entre medio,
                         * es decir, crea los directorios y demás si no existen hasta llegar al destino */
                        Files.createDirectories(Path.of(Path.of("Boletin_Tema6/src/Boletin2/Ejercicio6B2/" +
                                m.group("CURSO")) + "/" + m.group("APELLIDO1") + m.group("APELLIDO2")
                                + m.group("NOMBRE")));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                /*lineas.stream().map(pattern::matcher).filter(Matcher::matches).forEach(m -> {
                    Path directorio = Path.of("Boletin_Tema6/src/Boletin2/Ejercicio6B2/"
                            + m.group("CURSO"));
                    if (!Files.exists(directorio)) {
                        try {
                            Files.createDirectory(directorio);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    Path fichero = Path.of("Boletin_Tema6/src/Boletin2/Ejercicio6B2/" + m.group("CURSO") +
                            "/" + m.group("APELLIDO1") + m.group("APELLIDO2") + m.group("NOMBRE"));
                    if (!Files.exists(fichero)) {
                        try {
                            Files.createFile(fichero);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });*/
            }

        } catch (RuntimeException | IOException e) {
            throw new Ejercicio6B2Excepcion(e.getMessage());
        }
    }
}