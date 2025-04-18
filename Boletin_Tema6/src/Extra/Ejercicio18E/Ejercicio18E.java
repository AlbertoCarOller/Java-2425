package Extra.Ejercicio18E;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio18E {
    public static void main(String[] args) {
        try {
            //crearFicherosValidosInvalidos();
            crearFicherosValidosInvalidosV2();

        } catch (Ejercicio18EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a escribir en Validos.txt las líneas que cumplan con las especificaciones
     * en caso de que una línea no cumpla con la validación se enviará a Invalidos.txt
     *
     * @throws Ejercicio18EException
     */
    public static void crearFicherosValidosInvalidos() throws Ejercicio18EException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio18E/Personas.txt");
            Path escribirV = Path.of("Boletin_Tema6/src/Extra/Ejercicio18E/Validos.txt");
            Path escribirI = Path.of("Boletin_Tema6/src/Extra/Ejercicio18E/Invalidos.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pwV = new PrintWriter(new FileWriter(escribirV.toFile()));
                 PrintWriter pwI = new PrintWriter(new FileWriter(escribirI.toFile()))) {
                String linea;
                /* Cuando se hace el find, hay que especificar cuando empieza la expresión y cuando termina
                 * por ejemplo si tenemos 4 expresiones, tenemos que colocar ^ donde corresponda, la primera
                 * expresión que aparece en la línea y deberemos de poner únicamente el $ en la última
                 * expresión regular que va a estar en la línea */
                List<Pattern> comprobaciones = List.of(Pattern.compile("^\\p{Lu}\\p{Ll}{2,}"),
                        Pattern.compile("\\p{Lu}\\p{Ll}{2,}"),
                        Pattern.compile("\\p{Lu}\\p{Ll}{2,}"), Pattern.compile("[1-9][0-9]*"),
                        Pattern.compile("[MF]"),
                        Pattern.compile("[0-9]{8}[A-Z]"),
                        Pattern.compile("[a-z][a-z._-]+@(gmail\\.com|outlook\\.com|hotmail\\.(es|com))$"));
                Matcher matcher;
                while ((linea = br.readLine()) != null) {
                    boolean alMenosUno = false;
                    StringBuilder sb = new StringBuilder();
                    sb.append(linea);
                    for (int i = 0; i < comprobaciones.size(); i++) {
                        matcher = comprobaciones.get(i).matcher(linea);
                        /* El .find() cualquier coincidencia que encuentre la dará por válida, da igual que esté al
                         principio, al final o en medio, esto puede ser un problema si por ejemplo tenemos un correo
                         que empieza por un número y este no es válido, pero el resto está bien, devolverá true, porque
                         hay una coincidencia*/
                        if (!matcher.find()) {
                            alMenosUno = true;
                            if (i == 0) {
                                sb.append("\nEl nombre está mal");

                            } else if (i == 1) {
                                sb.append("\nEl primer apellido no es correcto");

                            } else if (i == 2) {
                                sb.append("\nEl segundo apellido no es correcto");

                            } else if (i == 3) {
                                sb.append("\nLa edad no es correcta");

                            } else if (i == 4) {
                                sb.append("\nEl sexo no es correcto");

                            } else if (i == 5) {
                                sb.append("\nEl DNI no es correcto");

                            } else {
                                sb.append("\nEl correo no es correcto");
                            }
                        }
                    }
                    if (alMenosUno) {
                        pwI.println(sb);

                    } else {
                        pwV.println(sb);
                    }
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio18EException(e.getMessage());
        }
    }

    /**
     * Este método va a escribir en Validos.txt las líneas que cumplan con las especificaciones
     * en caso de que una línea no cumpla con la validación se enviará a Invalidos.txt
     * (segunda version)
     *
     * @throws Ejercicio18EException
     */
    public static void crearFicherosValidosInvalidosV2() throws Ejercicio18EException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio18E/Personas.txt");
            Path escribirleV = Path.of("Boletin_Tema6/src/Extra/Ejercicio18E/Validos.txt");
            Path escribirleI = Path.of("Boletin_Tema6/src/Extra/Ejercicio18E/Invalidos.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pwV = new PrintWriter(new FileWriter(escribirleV.toFile()));
                 PrintWriter pwI = new PrintWriter(new FileWriter(escribirleI.toFile()))) {
                List<Pattern> comprobaciones = List.of(Pattern.compile("^\\p{Lu}\\p{Ll}{2,}$", Pattern.UNICODE_CHARACTER_CLASS),
                        Pattern.compile("^\\p{Lu}\\p{Ll}{2,}$", Pattern.UNICODE_CHARACTER_CLASS),
                        Pattern.compile("^\\p{Lu}\\p{Ll}{2,}$", Pattern.UNICODE_CHARACTER_CLASS), Pattern.compile("^[1-9][0-9]$"),
                        Pattern.compile("^[MF]$"),
                        Pattern.compile("^[0-9]{8}[A-Z]$"),
                        Pattern.compile("^[a-z][a-z._-]{2,}@(gmail\\.com|outlook\\.com|hotmail\\.(es|com))$"));
                Matcher matcher;
                String linea;
                while ((linea = br.readLine()) != null) {
                    boolean alMenosUna = false;
                    StringBuilder sb = new StringBuilder(linea);
                    String[] partes = linea.split(" ");
                    for (int i = 0; i < comprobaciones.size(); i++) {
                        boolean matchEncontrado = false;
                        for (int j = 0; j < partes.length; j++) {
                            matcher = comprobaciones.get(i).matcher(partes[j]);
                            if (matcher.matches()) {
                                matchEncontrado = true;
                            }
                        }
                        if (!matchEncontrado) {
                            if (!alMenosUna) {
                                alMenosUna = true;
                            }
                            if (i == 0) {
                                sb.append("\nEl nombre no es correcto");

                            } else if (i == 1) {
                                sb.append("\nEl primer apellido no es correcto");

                            } else if (i == 2) {
                                sb.append("\nEl segundo apellido no es correcto");

                            } else if (i == 3) {
                                sb.append("\nLa edad no es correcta");

                            } else if (i == 4) {
                                sb.append("\nEl sexo no es correcto");

                            } else if (i == 5) {
                                sb.append("\nEl dni no es correcto");

                            } else {
                                sb.append("\nEl correo no es correcto");
                            }
                        }
                    }
                    if (alMenosUna) {
                        pwI.println(sb);

                    } else {
                        pwV.println(sb);
                    }
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio18EException(e.getMessage());
        }
    }
}