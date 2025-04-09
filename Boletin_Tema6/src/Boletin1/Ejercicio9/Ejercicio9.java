package Boletin1.Ejercicio9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio9 {
    public static void main(String[] args) {
        try {
            crearFicheroMatriculas();

        } catch (Ejercicio9Excepcion e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a crear un fichero llamado 'MatriculasCorrectas.txt' en este va a escribir
     * las matrículas del fichero 'Matricula.txt' que tengan un correcto formato de matrícula como
     * especifica el enunciado, esto lo hacemos mediante un filtro y el uso de expresiones regulares
     * y lambdas
     *
     * @throws Ejercicio9Excepcion
     */
    public static void crearFicheroMatriculas() throws Ejercicio9Excepcion {
        try {
            // Creamos el fichero que va a contener las matrículas con un buen formato
            Path ficheroNuevo = Files.createFile(Path
                    .of("Boletin_Tema6/src/Boletin1/Ejercicio9/MatriculasCorrectas.txt"));
            /* Creamos el reader y el writer que van a hacer el traspaso de información
             * entre los dos ficheros */
            try (BufferedReader bf = new BufferedReader(
                    new FileReader("Boletin_Tema6/src/Boletin1/Ejercicio9/Matricula.txt"));
                 PrintWriter pw = new PrintWriter(new PrintWriter(ficheroNuevo.toFile()))) {
                // En este conjunto vamos a guardar todas las matrículas
                Set<String> matriculas = new HashSet<>();
                // Creamos una variable donde almacenar la matrícula
                String matricula;
                // Mientras haya líneas introducimos en el conjunto las matrículas
                while ((matricula = bf.readLine()) != null) {
                    String[] s = matricula.split(" ");
                    matricula = s[s.length - 1];
                    matriculas.add(matricula);
                }
                // Nos quedamos con las matrículas que cumplan el formato y las escribimos en el fichero nuevo
                matriculas.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^[0-9]{4}-[A-Z]{3}$");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(pw::println);
            }

        } catch (IOException | InvalidPathException e) {
            throw new Ejercicio9Excepcion(e.getMessage());
        }
    }
}