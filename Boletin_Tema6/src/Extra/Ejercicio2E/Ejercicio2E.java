package Extra.Ejercicio2E;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio2E {
    public static void main(String[] args) {
        try {
            validarCodigoPostal();

        } catch (Ejercicio2EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Hacemos un método que va a comprobar que los códigos postal sean válidos, en caso
     * de que los códigos del fichero sean válidos se pasará a un fichero nuevo
     *
     * @throws Ejercicio2EException
     */
    public static void validarCodigoPostal() throws Ejercicio2EException {
        try {
            Path leer = Path.of("Boletin_Tema6/src/Extra/Ejercicio2E/CodigoPostal.txt");
            Path escribir = Files.createFile(
                    Path.of("Boletin_Tema6/src/Extra/Ejercicio2E/CodigoPostalValidos.txt"));
            try (BufferedReader bf = new BufferedReader(new FileReader(leer.toFile()));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribir.toFile()))) {
                Set<String> codigosPostal = new HashSet<>();
                String codigoPostal;
                while ((codigoPostal = bf.readLine()) != null) {
                    String[] s = codigoPostal.split(" ");
                    codigoPostal = s[s.length - 1];
                    codigosPostal.add(codigoPostal);
                }
                codigosPostal.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^[0-9]{5}$");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(pw::println);
            }

        } catch (IOException e) {
            throw new Ejercicio2EException(e.getMessage());
        }
    }
}