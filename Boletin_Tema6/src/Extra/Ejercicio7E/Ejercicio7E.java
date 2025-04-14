package Extra.Ejercicio7E;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio7E {
    public static void main(String[] args) {
        try {
            validarFechas();

        } catch (Ejercicio7EException e) {
            throw new RuntimeException(e);
        }
    }

    public static void validarFechas() throws Ejercicio7EException {
        try {
            Path fichero = Path.of("Boletin_Tema6/src/Extra/Ejercicio7E/Fechas.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(fichero.toFile()))) {
                Set<String> fechas = new HashSet<>();
                String fecha;
                while ((fecha = br.readLine()) != null) {
                    fechas.add(fecha);
                }
                fechas.stream().filter(f -> {
                    Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19[0-9]{2}|20[0-9]{2})$");
                    Matcher matcher = pattern.matcher(f);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(System.out::println);
            }

        } catch (IOException | InvalidPathException e) {
            throw new Ejercicio7EException(e.getMessage());
        }
    }
}