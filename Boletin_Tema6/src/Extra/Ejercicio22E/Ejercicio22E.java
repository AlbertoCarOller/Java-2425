package Extra.Ejercicio22E;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio22E {
    public static void main(String[] args) {
        try {
            validarInformacionPersonas();

        } catch (Ejercicio22EExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a comprobar la validéz de la información en el fichero especificado
     * en caso de que sea válido pasa a un fichero la información tal cual, en caso de que
     * sea inválido se mandará a otro fichero y con el motivo o los motivos de su
     * invalidéz
     *
     * @throws Ejercicio22EExcepcion
     */
    public static void validarInformacionPersonas() throws Ejercicio22EExcepcion {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio22E/InformacionPersonas.txt");
            Path escribirV = Path.of("Boletin_Tema6/src/Extra/Ejercicio22E/ValidosInfo.txt");
            Path escribirI = Path.of("Boletin_Tema6/src/Extra/Ejercicio22E/InvalidosInfo.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pwV = new PrintWriter(new FileWriter(escribirV.toFile()));
                 PrintWriter pwI = new PrintWriter(new FileWriter(escribirI.toFile()))) {
                List<Pattern> comprobaciones = List.of(Pattern.compile("^\\p{Lu}\\p{Ll}{2,}$", Pattern.UNICODE_CHARACTER_CLASS),
                        Pattern.compile("^[1-9][0-9]$"));
                String linea;
                Matcher matcher;
                while ((linea = br.readLine()) != null) {
                    boolean alMenosUn = false;
                    StringBuilder sb = new StringBuilder(linea.trim());
                    String[] partes = linea.trim().split(" ");
                    Pattern pattern = Pattern.compile("^[A-za-z]*[p\\p{Z}\\p{S}\\p{P}\\p{N}]+[a-z]*$");
                    boolean primeraVezPuntuacion = true;
                    if (partes.length != 4) {
                        sb.append("\nNo están las 4 bloques de información");
                        alMenosUn = true;
                    }
                    for (int i = 0; i < comprobaciones.size(); i++) {
                        boolean match = false;
                        for (int j = 0; j < partes.length; j++) {
                            Matcher matcher1 = pattern.matcher(partes[j]);
                            Matcher matcher2 = comprobaciones.get(1).matcher(partes[j]);
                            if (matcher1.matches() && primeraVezPuntuacion && !matcher2.matches()) {
                                sb.append("\nHay signos de puntuación, espacios, símbolos no permitidos o números");
                                alMenosUn = true;
                                primeraVezPuntuacion = false;
                            }
                            matcher = comprobaciones.get(i).matcher(partes[j]);
                            if (matcher.matches()) {
                                match = true;
                            }
                        }
                        if (!match) {
                            alMenosUn = true;
                            if (i == 0) {
                                sb.append("\nEl nombre y apellidos no están");

                            } else {
                                sb.append("\nLa edad no es correcta o no está");
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
            throw new Ejercicio22EExcepcion(e.getMessage());
        }
    }
}