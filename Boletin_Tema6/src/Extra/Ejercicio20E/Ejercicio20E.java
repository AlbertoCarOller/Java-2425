package Extra.Ejercicio20E;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio20E {
    public static void main(String[] args) {
        try {
            validarMatriculasConRazones();

        } catch (Ejercicio20EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Hacemos un método para validar las matrículas del fichero especificado e imprimimos
     * las matrículas correctas en matriculasValidas.txt y las que no sean correctas las
     * imprimimos en matriculasInvalidas.txt con la razón de su invalidéz
     *
     * @throws Ejercicio20EException
     */
    public static void validarMatriculasConRazones() throws Ejercicio20EException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio20E/matriculas.txt");
            Path escribirV = Path.of("Boletin_Tema6/src/Extra/Ejercicio20E/matriculasValidas.txt");
            Path escribirI = Path.of("Boletin_Tema6/src/Extra/Ejercicio20E/matriculasInvalidas.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pwV = new PrintWriter(new FileWriter(escribirV.toFile()));
                 PrintWriter pwI = new PrintWriter(new FileWriter(escribirI.toFile()))) {
                String linea;
                List<Pattern> comprobaciones = List.of(Pattern.compile("^\\d{4}"), Pattern.compile("-"),
                        Pattern.compile("[A-Z]{3}$"));
                Matcher matcher;
                while ((linea = br.readLine()) != null) {
                    boolean alMenosUn = false;
                    StringBuilder sb = new StringBuilder(linea);
                    String[] partes = linea.split(" ");
                    for (int i = 0; i < comprobaciones.size(); i++) {
                        matcher = comprobaciones.get(i).matcher(partes[partes.length - 1]);
                        if (!matcher.find()) {
                            alMenosUn = true;
                            if (i == 0) {
                                sb.append("\nNo empieza por 4 dígitos");

                            } else if (i == 1) {
                                sb.append("\nNo tiene el guión");

                            } else {
                                sb.append("\nNo termina en 3 letras mayúsculas");
                            }
                        }
                    }
                    if (alMenosUn) {
                        pwI.println(sb);

                    } else {
                        pwV.println(sb);
                    }
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio20EException(e.getMessage());
        }
    }
}