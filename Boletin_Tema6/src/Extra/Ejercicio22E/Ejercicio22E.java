package Extra.Ejercicio22E;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
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
                // Pattern para validar el nombre y apellidos
                Pattern nombreYApellidos = Pattern.compile("^\\p{Lu}\\p{Ll}+$");
                // Pattern para validar la edad
                Pattern edad = Pattern.compile("^[1-9][0-9]$");
                Matcher matcher;
                String linea;
                while ((linea = br.readLine()) != null) {
                    StringBuilder sb = new StringBuilder(linea);
                    String[] partes = linea.trim().split(" ");
                    // Esto nos indicará si al menos hay un fallo
                    boolean alMenosUn = false;
                    // Si no hay 4 partes, se añadirá un error
                    if (partes.length != 4) {
                        sb.append("\nNo hay 4 partes");
                        alMenosUn = true;
                    }
                    // Recorremos las partes de la línea
                    for (int i = 0; i < partes.length; i++) {
                        // En caso de que sea una de las 3 primeras partes utilizaremos el patter del nombre y apellidos
                        if (i <= 2) {
                            matcher = nombreYApellidos.matcher(partes[i]);
                            // Si no hay match miramos cuál será la parte concreta para mostrar un mensaje personalizado
                            if (!matcher.matches()) {
                                alMenosUn = true;
                                if (i == 0) {
                                    sb.append("\nEl nombre está mal");

                                } else if (i == 1) {
                                    sb.append("\nEl primer apellido está mal");

                                } else {
                                    sb.append("\nEl segundo apellido está mal");
                                }
                            }

                            // Si es mayor a 2 corresponde a la parte de la edad
                        } else {
                            matcher = edad.matcher(partes[i]);
                            if (!matcher.matches()) {
                                alMenosUn = true;
                                sb.append("\nLa edad no es correcta");
                            }
                        }
                    }
                    // Si hay al menos un fallo se imprimirá en 'InvalidosInfo.txt'
                    if (alMenosUn) {
                        pwI.println(sb);

                        // En caso de que no haya ningún fallo se imprimirá en 'ValidosInfo.txt'
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