package Extra.Ejercicio3E;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio3E {
    public static void main(String[] args) {
        try {
            validacionDni();

        } catch (Ejercicio3EExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método valida los dni de un fichero y los que sean correctos se guardan en
     * un fichero nuevo
     *
     * @throws Ejercicio3EExcepcion
     */
    public static void validacionDni() throws Ejercicio3EExcepcion {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio3E/DNI.txt");
            Path escribir = Files.createFile(Path.of("Boletin_Tema6/src/Extra/Ejercicio3E/DNIValidos.txt"));
            try (BufferedReader bf = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribir.toFile()))) {
                Set<String> dnis = new HashSet<>();
                String dni;
                while ((dni = bf.readLine()) != null) {
                    String[] s = dni.split(" ");
                    dni = s[s.length - 1];
                    dnis.add(dni);
                }
                dnis.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^[0-9]{8}[A-Z]$");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        if (dniCorrecto(Integer.parseInt(s.substring(0, 8)) % 23, s.charAt(s.length() - 1))) {
                            return true;
                        }
                    }
                    return false;
                }).forEach(pw::println);
            }

        } catch (IOException | InvalidPathException e) {
            throw new Ejercicio3EExcepcion(e.getMessage());
        }
    }

    /**
     * Este método va a hacer un mapa con el resto correspondiente y con
     * las letras que corresponda a cada letra y va a comprobar
     * que sea correcta la letra con el dni
     *
     * @return boolean
     * @throws Ejercicio3EExcepcion
     */
    public static boolean dniCorrecto(int resto, char letra) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        Map<Integer, Character> mapaDni = new HashMap<>();
        for (int i = 0; i < letras.length(); i++) {
            mapaDni.put(i, letras.charAt(i));
        }
        return mapaDni.get(resto) == letra;
    }
}