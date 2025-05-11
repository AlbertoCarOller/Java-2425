package Extra.ExamenPracticaV5;

import Extra.ExamenPracticaV4.GeneradorInformesLibroException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ExamenPracticaV5 {
    public static void main(String[] args) {
        try {
            List<Path> ficherosValidos = validarFicheros();
            copiarYCrearValidos(ficherosValidos);
            normalizarFichero(ficherosValidos);
            crearResumen(resumenValidos(ficherosValidos));
            transformarXML(ficherosValidos);
            System.out.println("Usuario con más acciones: " + obtenerUsuarioConMasAcciones());

        } catch (ExamenPracticaV5Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Nos quedamos con los ficheros válidos que cumplan con los requisitos
     * especificados
     *
     * @return una lista con los ficheros válidos
     * @throws ExamenPracticaV5Exception
     */
    public static List<Path> validarFicheros() throws ExamenPracticaV5Exception {
        try {
            Path directorioInicial = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV5/documentos_originales");
            try (Stream<Path> ficheros = Files.walk(directorioInicial)) {
                // Repasamos todos los ficheros del directorio inicial
                return ficheros.filter(Files::isRegularFile).filter(p -> {
                    try {
                        // Si es un .txt, tiene al menos 5 líneas y contiene login en alguna parte entra
                        if (p.toFile().getName().matches(".+\\.txt") && Files.readAllLines(p).size() >= 5
                                && Files.readString(p).toLowerCase().contains("login")) {
                            return comprobarLineas(p);
                        }
                        return false;

                    } catch (IOException ignored) {
                        return false;
                    }
                }).toList();
            }

        } catch (InvalidPathException | IOException e) {
            throw new ExamenPracticaV5Exception(e.getMessage());
        }
    }

    /**
     * Este método es un auxiliar del anterior que va a comprobar
     * línea por línea
     *
     * @param p el fichero repasado
     * @return si pasa o no
     */
    public static boolean comprobarLineas(Path p) {
        try (BufferedReader br = new BufferedReader(new FileReader(p.toFile()))) {
            Pattern pattern = Pattern.compile("(?i)^LOGIN");
            String linea;
            while ((linea = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find()) {
                    return true;
                }
            }
            return false;

        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Este método va a crear el directorio donde se encuentran
     * los ficheros válidos también copiados aquí
     *
     * @param ficheros una lista de ficheros válidos
     * @throws ExamenPracticaV5Exception
     */
    public static void copiarYCrearValidos(List<Path> ficheros) throws ExamenPracticaV5Exception {
        try {
            Path directorioInicio = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV5/documentos_originales");
            Path directorioDestino = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV5/procesados");
            ficheros.forEach(p -> {
                if (!Files.exists(directorioDestino)) {
                    try {
                        Files.createDirectory(directorioDestino);

                    } catch (IOException ignored) {
                    }
                }
                Path pathEstructura = directorioInicio.relativize(p.getParent());
                Path pathCasiCompleto = directorioDestino.resolve(pathEstructura);
                if (!Files.exists(pathCasiCompleto)) {
                    try {
                        // Creamos las carpetas intermedias
                        Files.createDirectories(pathCasiCompleto);

                    } catch (IOException ignored) {
                    }
                }
                try {
                    Files.copy(p, pathCasiCompleto.resolve(p.toFile().getName()));

                } catch (IOException ignored) {
                }
            });

        } catch (InvalidPathException e) {
            throw new ExamenPracticaV5Exception(e.getMessage());
        }
    }

    /**
     * Este método va a crear un fichero donde se guarde todos
     * los ficheros normalizados con el formato correspondiente
     *
     * @param ficheros la lista de ficheros válidos
     * @throws ExamenPracticaV5Exception
     */
    public static void normalizarFichero(List<Path> ficheros) throws ExamenPracticaV5Exception {
        try {
            Path directorioInicio = Path.of("Boletin_tema6/src/Extra/ExamenPracticaV5/documentos_originales");
            Path directorioNormalizacion = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV5/normalizaciones");
            ficheros.forEach(p -> {
                if (!Files.exists(directorioNormalizacion)) {
                    try {
                        Files.createDirectory(directorioNormalizacion);

                    } catch (IOException ignored) {
                    }
                }
                Path pathCasiCompleto = directorioNormalizacion.resolve(directorioInicio.relativize(p.getParent()));
                if (!Files.exists(pathCasiCompleto)) {
                    try {
                        Files.createDirectories(pathCasiCompleto);

                    } catch (IOException ignored) {
                    }
                }
                try (PrintWriter pw = new PrintWriter(new FileWriter(pathCasiCompleto.resolve(p.toFile()
                        .getName()).toFile()))) {
                    escribirContenido(pw, p);

                } catch (IOException ignored) {
                }
            });

        } catch (InvalidPathException e) {
            throw new ExamenPracticaV5Exception(e.getMessage());
        }
    }

    /**
     * Este es un método auxiliar del anterior que va a
     * escribir dentro del fichero
     *
     * @param pw el PrintWriter del fichero nuevo creado
     * @param p  el Path original repasado
     */
    public static void escribirContenido(PrintWriter pw, Path p) {
        try (Stream<String> lineas = Files.lines(p)) {
            Pattern pattern = Pattern.compile("\\b[\\p{L}\\d\\p{P}]+$");
            Pattern pattern1 = Pattern.compile("(?i)^DELETE");
            lineas.forEach(l -> {
                Matcher matcher = pattern.matcher(l.trim());
                Matcher matcher1 = pattern1.matcher(matcher.replaceAll(m -> "usuario_oculto"));
                pw.println(matcher1.replaceAll(m -> "ELIMINADO"));
            });
        } catch (IOException ignored) {
        }
    }

    /**
     * Este método va a recorrer todos los ficheros y de cada fichero sus líneas,
     * haciendo los regex de login y nombre del usuario
     *
     * @param ficherosValidos los ficheros válidos
     * @return un mapa de los usuarios con su número de logins
     * @throws ExamenPracticaV5Exception
     */
    public static Map<String, Integer> resumenValidos(List<Path> ficherosValidos) throws ExamenPracticaV5Exception {
        try {
            Map<String, Integer> usuariosLogins = new HashMap<>();
            Pattern nombre = Pattern.compile("\\b([\\p{L}\\d\\p{P}]+)$");
            Pattern accion = Pattern.compile("(?i)^LOGIN");
            ficherosValidos.forEach(p -> auxiliarValidos(usuariosLogins, p, nombre, accion));
            return usuariosLogins;

        } catch (InvalidPathException e) {
            throw new ExamenPracticaV5Exception(e.getMessage());
        }
    }

    /**
     * Este método es un auxiliar del anterior, por cada línea del fichero
     * guarda los usuarios aún no registrados sumando el número de veces
     * que se repite la acción login
     *
     * @param usuariosLogins un mapa al cuál se le va a ir añadiendo
     *                       la información
     * @param p              el Path que se está repasando
     * @param nombre         el Pattern nombre
     * @param accion         el Patter acción
     */
    public static void auxiliarValidos(Map<String, Integer> usuariosLogins, Path p, Pattern nombre, Pattern accion) {
        try (Stream<String> lineas = Files.lines(p)) {
            lineas.forEach(l -> {
                Matcher nombreM = nombre.matcher(l);
                Matcher accionM = accion.matcher(l);
                if (nombreM.find()) {
                    if (!usuariosLogins.containsKey(nombreM.group(1))) {
                        usuariosLogins.put(nombreM.group(1), (int) accionM.results().count());

                    } else {
                        if (accionM.find()) {
                            usuariosLogins.replace(nombreM.group(1), usuariosLogins.get(nombreM.group(1)) + 1);
                        }
                    }
                }
            });

        } catch (IOException ignored) {
        }
    }

    /**
     * Este método va a crear un directorio y dentro de este creamos un fichero
     * donde se guarda los usuarios con el número de logins que tiene
     *
     * @param usuariosLogins el mapa que contiene la información
     * @throws ExamenPracticaV5Exception
     */
    public static void crearResumen(Map<String, Integer> usuariosLogins) throws ExamenPracticaV5Exception {
        try {
            Path directorioResumen = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV5/procesados/resumen");
            if (!Files.exists(directorioResumen)) {
                Files.createDirectory(directorioResumen);
            }
            try (PrintWriter pw = new PrintWriter(new FileWriter(directorioResumen.resolve("resumen.txt")
                    .toFile()))) {
                usuariosLogins.keySet().forEach(u -> {
                    pw.println("Usuario: " + u + ", LOGINS: " + usuariosLogins.get(u));
                });
            }

        } catch (InvalidPathException | IOException e) {
            throw new ExamenPracticaV5Exception(e.getMessage());
        }
    }

    /**
     * Este método guarda los cambios realizados en un archivo XML y lo
     * indenta
     *
     * @param doc        el Document
     * @param archivoXML el archivo XML destino
     * @throws GeneradorInformesLibroException
     */
    public static void guardarCambiosIndent(Document doc, Path archivoXML) throws ExamenPracticaV5Exception {
        try {
            // Guardamos los cambios
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoXML.toFile());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(domSource, streamResult);

        } catch (InvalidPathException | TransformerException e) {
            throw new ExamenPracticaV5Exception(e.getMessage());
        }
    }

    /**
     * Este método va a transformar los usuarios a XML
     *
     * @param ficherosValidos los ficheros válidos
     * @throws ExamenPracticaV5Exception
     */
    public static void transformarXML(List<Path> ficherosValidos) throws ExamenPracticaV5Exception {
        try {
            Path archivoXML = Path.of("Boletin_tema6/src/Extra/ExamenPracticaV5/usuarios.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element usuarios = doc.createElement("usuarios");
            doc.appendChild(usuarios);
            Pattern pattern = Pattern.compile("([A-Z]+)\\s(\\[\\d{2}:\\d{2}:\\d{2}])\\s([\\p{L}\\d\\p{P}]+)");
            List<String> usuariosXML = new ArrayList<>();
            ficherosValidos.forEach(p -> {
                try (Stream<String> lineas = Files.lines(p)) {
                    lineas.forEach(l -> {
                        Matcher matcher = pattern.matcher(l);
                        if (matcher.matches()) {
                            // Creamos los elementos
                            Element usuario = doc.createElement("usuario");
                            Element accion = doc.createElement("accion");
                            accion.setTextContent(matcher.group(1));
                            Element hora = doc.createElement("hora");
                            hora.setTextContent(matcher.group(2));
                            Element nombre = doc.createElement("nombre");
                            nombre.setTextContent(matcher.group(3));
                            try {
                                auxiliarXML(usuariosXML, usuario, nombre, hora, accion, usuarios, doc);

                            } catch (ExamenPracticaV5Exception ignored) {
                            }
                        }
                    });

                } catch (IOException e) {
                }
            });
            // Guardamos los cambios
            guardarCambiosIndent(doc, archivoXML);

        } catch (InvalidPathException | ParserConfigurationException | ExamenPracticaV5Exception e) {
            throw new ExamenPracticaV5Exception(e.getMessage());
        }
    }

    /**
     * Este método comprobará si se puede añadir el usuario o si por lo
     * contrario, se le añadirán algunos campos al usuario ya existente,
     * es un método auxiliar
     *
     * @param usuariosXML lista de nombres de usuarios
     * @param usuario     el elemento usuario repasado
     * @param nombre      el elemento nombre
     * @param hora        el elemento hora
     * @param accion      el elemento accion
     * @param usuarios    el nodo raíz
     * @param doc         el Document
     * @throws ExamenPracticaV5Exception
     */
    public static void auxiliarXML(List<String> usuariosXML, Element usuario, Element nombre, Element hora,
                                   Element accion, Element usuarios, Document doc) throws ExamenPracticaV5Exception {
        try {
            // Si el usuario no se ha añadido ya, se añade
            if (!usuariosXML.contains(nombre.getTextContent())) {
                // Añadimos los nodos
                usuario.appendChild(nombre);
                usuario.appendChild(hora);
                usuario.appendChild(accion);
                usuarios.appendChild(usuario);
                usuariosXML.add(nombre.getTextContent());

                // Si ya está añadido, añadimos los datos a estos usuarios ya existentes
            } else {
                NodeList usuariosList = doc.getDocumentElement().getElementsByTagName("usuario");
                for (int i = 0; i < usuariosList.getLength(); i++) {
                    Element usuarioE = (Element) usuariosList.item(i);
                    if (usuarioE.getElementsByTagName("nombre").item(0).getTextContent()
                            .equals(nombre.getTextContent())) {
                        Element accionE = (Element) usuarioE.getElementsByTagName("accion").item(0);
                        accionE.setTextContent(accionE.getTextContent().concat(" " + accion.getTextContent()));
                        Element horaE = (Element) usuarioE.getElementsByTagName("hora").item(0);
                        horaE.setTextContent(horaE.getTextContent().concat(" " + hora.getTextContent()));
                    }
                }
            }
        } catch (DOMException e) {
            throw new ExamenPracticaV5Exception(e.getMessage());
        }
    }

    /**
     * Este método va a obtener el nombre del usuario que tenga más acciones
     * <accion></accion>
     *
     * @return el nombre del que usuario
     * @throws ExamenPracticaV5Exception
     */
    public static List<String> obtenerUsuarioConMasAcciones() throws ExamenPracticaV5Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV5/usuarios.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList usuarios = doc.getDocumentElement().getElementsByTagName("usuario");
            Map<String, Integer> usuariosAcciones = new HashMap<>();
            Pattern pattern = Pattern.compile("\\b[A-Z]+\\b");
            for (int i = 0; i < usuarios.getLength(); i++) {
                Element usuario = (Element) usuarios.item(i);
                Matcher matcher = pattern.matcher(usuario.getElementsByTagName("accion")
                        .item(0).getTextContent());
                usuariosAcciones.put(usuario.getElementsByTagName("nombre").item(0).getTextContent(),
                        (int) matcher.results().count());
            }
            return usuariosAcciones.keySet().stream().filter(u -> usuariosAcciones.get(u).equals(usuariosAcciones.values()
                    .stream().max(Comparator.comparingInt(v -> v)).orElse(0))).toList();

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException e) {
            throw new ExamenPracticaV5Exception(e.getMessage());
        }
    }
}