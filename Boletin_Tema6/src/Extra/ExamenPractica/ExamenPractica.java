package Extra.ExamenPractica;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ExamenPractica {
    public static void main(String[] args) {
        try {
            pasarValidos();
            crearXML();
            mostrarUsuarios();
            System.out.println("Dominios: " + contarUsuariosConDominio());
            System.out.println();
            System.out.println("Usuario con DNI que empiece por 1234: " +
                    usuarioDniEmpiecePor1234().getElementsByTagName("nombre").item(0).getTextContent());

        } catch (ExamenPracticaException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a pasar los ficheros válidos y va a crear las carpetas especificadas,
     * se mantendrá la estructura
     *
     * @throws ExamenPracticaException
     */
    public static void pasarValidos() throws ExamenPracticaException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/ExamenPractica/datosEntrada");
            Path meterlo = Path.of("Boletin_Tema6/src/Extra/ExamenPractica/salida/usuarios_validos/");
            try (Stream<Path> ficheros = Files.walk(leerlo)) {
                ficheros.filter(p -> {
                    if (Files.isRegularFile(p)) {
                        if (p.toFile().getName().matches(".+\\.txt")) {
                            return true;
                        }
                    }
                    return false;
                }).forEach(p -> {
                    try {
                        // .getNameCount() -> da el número de segmentos de la ruta
                        int numeroSegmentos = leerlo.relativize(p).getNameCount() - 1;
                        if (numeroSegmentos > 0) {
                            /* .subpath() -> da un Path desde un incluido índice hasta otro excluido hay que tener
                             * cuidado porque si me da 0 .getNameCount() significa que no hay segmentos y al hacer
                             * .subpath() al ser el comienzo igual que el destino da error, porque no hay nada que
                             * extraer. También si no se cumple que begin < end dará error, porque no se extraería
                             * nada, sería solo el nombre del fichero por eso ponemos mayor que 1 en la condición de
                             * arriba */
                            Path pathSinDestino = leerlo.relativize(p).subpath(0, numeroSegmentos);
                            // resolve() -> te da la fusión de dos Path, la pasada por parámetros no debe ser absoluta
                            Path pathCasiCompleto = meterlo.resolve(pathSinDestino);
                            if (!Files.exists(pathCasiCompleto)) {
                                Files.createDirectories(pathCasiCompleto);
                            }
                            Files.copy(p, Path.of(pathCasiCompleto + "/" + p.toFile().getName()));

                            // Entrará si no tiene subcarpetas por lo que se podrá crear directamente
                        } else {
                            if (!Files.exists(meterlo)) {
                                Files.createDirectories(meterlo);
                            }
                            Files.copy(p, meterlo.resolve(p.toFile().getName()));
                        }

                    } catch (IOException e) {
                    }
                });
            }

        } catch (InvalidPathException | IOException e) {
            throw new ExamenPracticaException(e.getMessage());
        }
    }

    /**
     * Este método se va a quedar con las líneas válidas de cada archivo ya validado
     * se guardarán las correctas en una lista
     *
     * @return una lista con las líneas correctas
     * @throws ExamenPracticaException
     */
    public static List<String> lineasValidas() throws ExamenPracticaException {
        try {
            Path directorio = Path.of("Boletin_Tema6/src/Extra/ExamenPractica/salida/usuarios_validos");
            try (Stream<Path> ficheros = Files.walk(directorio)) {
                List<String> lineasValidas = new ArrayList<>();
                Pattern pattern = Pattern.compile("<((?:\\p{L}+\\s?){2,3})>, " +
                        "<([a-z0-9._-]+@(?:gmail|outlook)\\.com)>, <(\\d{8}[A-Z])>");
                ficheros.filter(Files::isRegularFile).forEach(p -> {
                    try (Stream<String> lineas = Files.lines(p)) {
                        lineas.forEach(l -> {
                            Matcher matcher = pattern.matcher(l);
                            if (matcher.matches()) {
                                lineasValidas.add(matcher.group(1) + "&" + matcher.group(2) + "&" + matcher.group(3));
                            }
                        });
                    } catch (IOException e) {
                    }
                });
                return lineasValidas;
            }

        } catch (InvalidPathException | IOException e) {
            throw new ExamenPracticaException(e.toString());
        }
    }

    /**
     * Este método va a crear el XML de los usuarios validados, pertenecientes
     * a las líneas válidas
     *
     * @throws ExamenPracticaException
     */
    public static void crearXML() throws ExamenPracticaException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPractica/usuarios.xml");
            Files.deleteIfExists(archivoXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element usuarios = doc.createElement("usuarios");
            doc.appendChild(usuarios);
            lineasValidas().forEach(l -> {
                Element usuario = doc.createElement("usuario");
                String[] partes = l.split("&");
                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(partes[0]);
                Element correo = doc.createElement("correo");
                correo.setTextContent(partes[1]);
                Element dni = doc.createElement("dni");
                dni.setTextContent(partes[2]);
                // Añadimos los nodos hijos al padre
                usuario.appendChild(nombre);
                usuario.appendChild(correo);
                usuario.appendChild(dni);
                // Añadimos el nodo padre
                usuarios.appendChild(usuario);
                // Guardamos los cambios
                DOMSource domSource = new DOMSource(doc);
                StreamResult streamResult = new StreamResult(archivoXML.toFile());
                try {
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                    transformer.transform(domSource, streamResult);

                } catch (TransformerException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (ParserConfigurationException | RuntimeException | IOException e) {
            throw new ExamenPracticaException(e.toString());
        }
    }

    /**
     * Este método va a mostrar los campos de cada usuario
     *
     * @throws ExamenPracticaException
     */
    public static void mostrarUsuarios() throws ExamenPracticaException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPractica/usuarios.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList usuarios = doc.getDocumentElement().getElementsByTagName("usuario");
            for (int i = 0; i < usuarios.getLength(); i++) {
                Element usuario = (Element) usuarios.item(i);
                NodeList camposUsuario = usuario.getChildNodes();
                for (int j = 0; j < camposUsuario.getLength(); j++) {
                    if (camposUsuario.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element campo = (Element) camposUsuario.item(j);
                        System.out.println(campo.getTagName() + ": " + campo.getTextContent());
                    }
                }
                System.out.println();
            }

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException e) {
            throw new ExamenPracticaException(e.getMessage());
        }
    }

    /**
     * Este método va a contar los dominios correctos según la especificación, como
     * cada usuario solo tiene un dominio, podemos coger todos los dominios del
     * documento directamente
     *
     * @return
     * @throws ExamenPracticaException
     */
    public static int contarUsuariosConDominio() throws ExamenPracticaException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPractica/usuarios.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList correos = doc.getDocumentElement().getElementsByTagName("correo");
            int numCorreos = 0;
            for (int i = 0; i < correos.getLength(); i++) {
                Element correo = (Element) correos.item(i);
                if (correo.getTextContent().matches("(?i).+@gmail\\.com")) {
                    numCorreos++;
                }
            }
            return numCorreos;

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException e) {
            throw new ExamenPracticaException(e.getMessage());
        }
    }

    /**
     * Este método va a devolver el primer nodo/element del usuario que su DNI comience
     * por 1234
     *
     * @return Element del usuario
     * @throws ExamenPracticaException
     */
    public static Element usuarioDniEmpiecePor1234() throws ExamenPracticaException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPractica/usuarios.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList usuarios = doc.getDocumentElement().getElementsByTagName("usuario");
            Element usuarioEncontrado = null;
            for (int i = 0; i < usuarios.getLength(); i++) {
                Element usuario = (Element) usuarios.item(i);
                Element dniUsuario = (Element) usuario.getElementsByTagName("dni").item(0);
                if (dniUsuario.getTextContent().matches("1234\\d+[A-Z]")) {
                    usuarioEncontrado = usuario;
                    break;
                }
            }
            if (usuarioEncontrado == null) {
                throw new ExamenPracticaException("No se ha encontrado al usuario");
            }
            return usuarioEncontrado;

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException e) {
            throw new ExamenPracticaException(e.getMessage());
        }
    }
}