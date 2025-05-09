package Extra.ExamenPracticaV2;

import Extra.ExamenPractica.ExamenPracticaException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ExamenPracticaV2 {
    public static void main(String[] args) {
        try {
            copiarFicherosValidos();
            normalizarFicheros();
            System.out.println(usuariosLogins());

        } catch (ExamenPracticaV2Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método validará los ficheros, tienen que ser ficheros .txt,
     * pesar más de 100 bytes y contener la palabra 'LOGIN'
     *
     * @return lista de los ficheros válidos
     * @throws ExamenPracticaV2Exception
     */
    public static List<Path> validarFicheros() throws ExamenPracticaV2Exception {
        try {
            Path directorio = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV2/reportes");
            try (Stream<Path> ficheros = Files.walk(directorio)) {
                // Miramos los path que sean únicamente ficheros
                return ficheros.filter(Files::isRegularFile).filter(p -> {
                    // Si el fichero es un .txt entra
                    if (p.toFile().getName().endsWith(".txt")) {
                        try {
                            // Si el fichero pesa más de 100 bytes entra
                            if (Files.size(p) > 100) {
                                // Repasamos las líneas de cada fichero
                                try (Stream<String> lineas = Files.lines(p)) {
                                    // Si se encuentra al menos una línea que contenga login entra
                                    if (lineas.anyMatch(l -> l.toLowerCase().contains("login"))) {
                                        // Devuelve true porque pasa el filtro
                                        return true;
                                    }
                                }
                            }
                        } catch (IOException e) {
                        }
                    }
                    // Devuelve false sino se ha devuelto true
                    return false;
                }).toList();
            }

        } catch (InvalidPathException | IOException e) {
            throw new ExamenPracticaV2Exception(e.getMessage());
        }
    }

    /**
     * Este método va a repasar fichero a fichero válido y va a llamar al método
     * auxiliar que se encargará de copiar
     *
     * @throws ExamenPracticaException
     */
    public static void copiarFicherosValidos() throws ExamenPracticaV2Exception {
        try {
            // El directorio donde se van a crear los directorios ya procesados
            Path directorioSalida = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV2/procesados");
            // El directorio inicial, donde están todos los archivos
            Path directorioInicial = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV2/reportes");
            // Repasamos los ficheros válidos
            validarFicheros().forEach(p -> {
                // En caso de que no exista el directorio principal de salida, entra
                if (!Files.exists(directorioSalida)) {
                    try {
                        // Se crea el directorio principal de salida
                        Files.createDirectory(directorioSalida);

                    } catch (IOException ignored) {
                    }
                    // Llamámos al método auxiliar
                    copiarAuxiliar(p, directorioInicial, directorioSalida);
                }
            });

        } catch (InvalidPathException | ExamenPracticaV2Exception e) {
            throw new ExamenPracticaV2Exception(e.getMessage());
        }
    }

    /**
     * Esto es un método auxiliar del anterior que por cada Path del flujo
     * va a copiar y crear las estructuras necesarias
     *
     * @param p                 el Path que está siendo recorrido
     * @param directorioInicial el directorio inicial
     * @param directorioSalida  el directorio de salida
     */
    public static void copiarAuxiliar(Path p, Path directorioInicial, Path directorioSalida) {
        try {
            Path estructuraRelativize = directorioInicial.relativize(p.getParent());
            Path pathCasiCompleto = directorioSalida.resolve(estructuraRelativize);
            if (!Files.exists(pathCasiCompleto)) {
                Files.createDirectories(pathCasiCompleto);
            }
            Files.copy(p, pathCasiCompleto.resolve(p.toFile().getName()), StandardCopyOption.REPLACE_EXISTING);

            // Ignoramos las excepciones para no cortar el flujo
        } catch (InvalidPathException | IOException ignored) {
        }
    }

    /**
     * Este método va a remplazar las palabras de las líneas que contengan
     * 'DELETE' por 'ELIMINADO' y nombre del usuario por 'usuario_oculto'
     * y se guardarán en un nuevo fichero que se creará por cada uno en una
     * nueva carpeta con las normalizaciones
     *
     * @throws ExamenPracticaV2Exception
     */
    public static void normalizarFicheros() throws ExamenPracticaV2Exception {
        try {
            Path directorioNormalizaciones = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV2/normalizaciones");
            Pattern pattern = Pattern.compile("\\s[a-z._-]+\\s");
            Pattern pattern1 = Pattern.compile("\\s(?i)DELETE\\s?");
            validarFicheros().forEach(p -> {
                if (!Files.exists(directorioNormalizaciones)) {
                    try {
                        Files.createDirectory(directorioNormalizaciones);

                    } catch (IOException ignored) {
                    }
                }
                Path pathNormalizado = directorioNormalizaciones.resolve(p.toFile().getName());
                try (Stream<String> lineas = Files.lines(p);
                     PrintWriter pw = new PrintWriter(new FileWriter(pathNormalizado.toFile()))) {
                    lineas.forEach(l -> {
                        Matcher matcher = pattern.matcher(l);
                        String lineaModificada = matcher.replaceAll(m -> " usuario_oculto ");
                        Matcher matcher1 = pattern1.matcher(lineaModificada);
                        pw.println(matcher1.replaceAll(m -> " ELIMINADO ").trim());
                    });

                } catch (IOException ignored) {
                }
            });
        } catch (InvalidPathException e) {
            throw new ExamenPracticaV2Exception(e.getMessage());
        }
    }

    // TODO: comentar este método y terminar el resto de métodos
    public static Map<String, Integer> usuariosLogins() throws ExamenPracticaV2Exception {
        try {
            Map<String, Integer> usuariosConLogins = new HashMap<>();
            Pattern pattern = Pattern.compile("\\s([a-z._-]+)\\s");
            Pattern pattern1 = Pattern.compile("\\s(?i)LOGIN\\s?");
            validarFicheros().forEach(p -> {
                try (Stream<String> lineas = Files.lines(p)) {
                    lineas.forEach(l -> {
                        Matcher matcher = pattern.matcher(l);
                        Matcher matcher1 = pattern1.matcher(l);
                        if (matcher.find()) {
                            if (!usuariosConLogins.containsKey(matcher.group(1))) {
                                usuariosConLogins.put(matcher.group(1), (int) matcher1.results().count());
                            }
                            if (matcher1.find()) {
                                usuariosConLogins.replace(matcher.group(1), usuariosConLogins.get(matcher.group(1)) + 1);
                            }
                        }
                    });
                } catch (IOException ignored) {
                }
            });
            return usuariosConLogins;

        } catch (InvalidPathException e) {
            throw new ExamenPracticaV2Exception(e.getMessage());
        }
    }
}