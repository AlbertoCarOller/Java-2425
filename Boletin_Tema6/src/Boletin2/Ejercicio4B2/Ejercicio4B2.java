package Boletin2.Ejercicio4B2;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio4B2 {
    public static void main(String[] args) {
        try {
            validarMatriculas();

        } catch (Ejercicio4B2Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a imprimir solo las matrículas que cumplan con la validación
     * específica y las cuales estén en una línea con una sola matrícula
     *
     * @throws Ejercicio4B2Exception
     */
    public static void validarMatriculas() throws Ejercicio4B2Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin2/Ejercicio4B2/TodasLasMatriculas.txt");
            Path escribirle = Path.of("Boletin_Tema6/src/Boletin2/Ejercicio4B2/SoloMatriculasCorrectas.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribirle.toFile()))) {
                Pattern pattern = Pattern.compile("^(?<MATRICULA>\\d{4}-[A-Z]{3})$");
                Matcher matcher;
                String linea;
                while ((linea = br.readLine()) != null) {
                    String matricula = "";
                    // Contador para saber el número de matrículas correctas que tiene una línea
                    int contadorMatriculas = 0;
                    String[] partes = linea.trim().split(" ");
                    for (int i = 0; i < partes.length; i++) {
                        matcher = pattern.matcher(partes[i]);
                        if (matcher.matches()) {
                            contadorMatriculas++;
                            matricula = matcher.group("MATRICULA");
                        }
                    }
                    // Si solo tiene una matrícula correcta, se imprimirá la matrícula
                    if (contadorMatriculas == 1) {
                        pw.println(matricula);
                    }
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio4B2Exception(e.getMessage());
        }
    }

    //TODO: hacer la segunda versión del método
}