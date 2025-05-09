package Extra.ExamenPracticaV2;

import Extra.ExamenPractica.ExamenPracticaException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
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
            crearResumenFichero(usuariosLogins());
            contarAdminYUsuarios();
            crearXMLModificado();
            System.out.println("Dominio que más se repite: " + obtenerEmailMasComun());

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

    /**
     * Este método va a guardar los usuarios con el número de logins de
     * todos los usuarios de los ficheros válidos
     *
     * @return un mapa con el nombre del usuario y el número de logins
     * @throws ExamenPracticaV2Exception
     */
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

    /**
     * Este método va a crear el directorio de resúmen
     * junto con un fichero que contendrá cada usuario
     * con el número de LOGINS
     *
     * @param usuarioLogins
     * @throws ExamenPracticaV2Exception
     */
    public static void crearResumenFichero(Map<String, Integer> usuarioLogins) throws ExamenPracticaV2Exception {
        try {
            Path directorioResumen = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV2/resumen");
            if (!Files.exists(directorioResumen)) {
                Files.createDirectory(directorioResumen);
            }
            Path ficheroResumen = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV2/resumen/resumenUsuarios.txt");
            try (PrintWriter pw = new PrintWriter(new FileWriter(ficheroResumen.toFile()))) {
                usuarioLogins.keySet().forEach(u -> pw.println("Usuario: " + u + ", LOGINS: " + usuarioLogins.get(u)));
            }

        } catch (InvalidPathException | IOException e) {
            throw new ExamenPracticaV2Exception(e.toString());
        }
    }

    /**
     * Este método cuenta los usuarios que hay en el XML y los usuarios
     * que son admins
     *
     * @throws ExamenPracticaV2Exception
     */
    public static void contarAdminYUsuarios() throws ExamenPracticaV2Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV2/usuarios.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList usuarios = doc.getDocumentElement().getElementsByTagName("usuario");
            int contadorUsuarios = 0;
            int contadorAdmins = 0;
            for (int i = 0; i < usuarios.getLength(); i++) {
                contadorUsuarios++;
                Element usuario = (Element) usuarios.item(i);
                Node rol = usuario.getElementsByTagName("rol").item(0);
                if (rol != null) {
                    Element rolE = (Element) rol;
                    if (rolE.getTextContent().matches("(?i)admin")) {
                        contadorAdmins++;
                    }
                }
            }
            System.out.println("Contador usuarios: " + contadorUsuarios);
            System.out.println("Contador admins: " + contadorAdmins);

        } catch (InvalidPathException | IOException | ParserConfigurationException | SAXException e) {
            throw new ExamenPracticaV2Exception(e.getMessage());
        }
    }

    /**
     * Este método va a crear un nuevo fichero XML a partir del fichero XML original
     * modificando el nombre (espacios por '-')
     *
     * @throws ExamenPracticaV2Exception
     */
    public static void crearXMLModificado() throws ExamenPracticaV2Exception {
        try {
            Path archivoXMLModificado = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV2/usuarios_modificados.xml");
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV2/usuarios.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList nombres = doc.getDocumentElement().getElementsByTagName("nombre");
            for (int i = 0; i < nombres.getLength(); i++) {
                Element nombre = (Element) nombres.item(i);
                nombre.setTextContent(nombre.getTextContent().replaceAll("\\s", "-"));
            }
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoXMLModificado.toFile());
            TransformerFactory.newInstance().newTransformer().transform(domSource, streamResult);

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException |
                 TransformerException e) {
            throw new ExamenPracticaV2Exception(e.getMessage());
        }
    }

    /**
     * Este método va a devolver el dominio de correo que más se repite
     * en todos los usuarios
     *
     * @return el dominio que más se repite
     * @throws ExamenPracticaV2Exception
     */
    public static String obtenerEmailMasComun() throws ExamenPracticaV2Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV2/usuarios.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList correos = doc.getDocumentElement().getElementsByTagName("email");
            Pattern pattern = Pattern.compile("@([a-z.]+)$");
            Map<String, Integer> correosVeces = new HashMap<>();
            // Recorremos todos los correos
            for (int i = 0; i < correos.getLength(); i++) {
                Element correo = (Element) correos.item(i);
                Matcher matcher = pattern.matcher(correo.getTextContent());
                // Si se encuentra un correo entra (esto es simplemente para poder utilizar el group)
                if (matcher.find()) {
                    // Si el dominio no está en el mapa se añade, con valor de 1, porque se ha encontrado
                    if (!correosVeces.containsKey(matcher.group(1))) {
                        correosVeces.put(matcher.group(1), 1);

                        // Si se encuentra en el mapa se añade 1 al valor que ya tenía
                    } else {
                        correosVeces.replace(matcher.group(1), correosVeces.get(matcher.group(1)) + 1);
                    }
                }
            }
            // Devolvemos el dominio que se repita más de una vez
            return correosVeces.keySet().stream().max(Comparator.comparingInt(correosVeces::get))
                    .orElseThrow(() -> new ExamenPracticaV2Exception("No hay emails"));

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException e) {
            throw new ExamenPracticaV2Exception(e.getMessage());
        }
    }
}