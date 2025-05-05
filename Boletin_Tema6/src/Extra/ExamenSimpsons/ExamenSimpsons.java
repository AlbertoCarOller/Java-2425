package Extra.ExamenSimpsons;

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
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ExamenSimpsons {
    public static void main(String[] args) {
        try {
            validarDirectorios();
            /*if (!validarDirectorios()) {
                throw new ExamenSimpsonsException("Alguno de los directorios no es válido");
            }*/
            copiarFicherosValidos();
            mostrarTituloYFechaMayorA1992();
            nuevoXMLSinopsisMas30();
            nuevoXMLSinopsisPersonajesPrincipales();

        } catch (ExamenSimpsonsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método valida que los directorios sean realmente directorios y tengan
     * los permisos correspondientes
     *
     * @throws ExamenSimpsonsException
     */
    public static void validarDirectorios() throws ExamenSimpsonsException {
        try {
            Path directorioInicio = Path.of("Boletin_Tema6/src/Extra/ExamenSimpsons/DirectorioConTodo");
            Path directorioDestino = Path.of("Boletin_Tema6/src/Extra/ExamenSimpsons/Directorio");
            if (!Files.isDirectory(directorioInicio) || !Files.isDirectory(directorioDestino)) {
                throw new ExamenSimpsonsException("Alguno de los directorios no son realmente directorios");
            }
            if (!Files.isReadable(directorioInicio)) {
                throw new ExamenSimpsonsException("El directorio inicio no tiene permisos de lectura");
            }
            if (!Files.isWritable(directorioDestino)) {
                throw new ExamenSimpsonsException("EL directorio destino no tiene permisos de lectura");
            }

        } catch (InvalidPathException e) {
            throw new ExamenSimpsonsException(e.getMessage());
        }
    }

    /*public static boolean validarDirectorios() throws ExamenSimpsonsException {
        try {
            Path directorioInicio = Path.of("Boletin_Tema6/src/Extra/ExamenSimpsons/DirectorioConTodo");
            Path directorioDestino = Path.of("Boletin_Tema6/src/Extra/ExamenSimpsons/Directorio");
            return Files.isDirectory(directorioInicio) && Files.isDirectory(directorioDestino) &&
                    Files.isReadable(directorioInicio) && Files.isWritable(directorioDestino);

        } catch (InvalidPathException e) {
            throw new ExamenSimpsonsException(e.getMessage());
        }
    }*/

    /**
     * Este método va a crear los ficheros válidos en el Path directorioDestino, solo los que cumplan
     * con las validaciones especificadas
     *
     * @throws ExamenSimpsonsException
     */
    public static void copiarFicherosValidos() throws ExamenSimpsonsException {
        try {
            Path directorioInicio = Path.of("Boletin_Tema6/src/Extra/ExamenSimpsons/DirectorioConTodo");
            Path directorioDestino = Path.of("Boletin_Tema6/src/Extra/ExamenSimpsons/Directorio");
            try (Stream<Path> flujo = Files.list(directorioInicio)) {
                flujo.filter(p -> {
                    try {
                        Pattern pattern = Pattern.compile("^(?i)copiar");
                        if (Files.isRegularFile(p)) {
                            Matcher matcher = pattern.matcher(Files.readString(p));
                            if (Files.size(p) > 1024 && p.toFile().getName().matches(".+\\.txt")
                                    && matcher.find()) {
                                return true;
                            }
                        }
                        return false;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).forEach(p -> {
                    try {
                        Path destino = Path.of(directorioDestino + "/" + p.toFile().getName());
                        // Paths.get() me devuelve un Path a partir de un String
                        //Path destino = Paths.get(directorioDestino + "/" + p.toFile().getName());
                        //Files.createFile(destino); -> esta línea sobra, ya que el .copy() te lo crea directamente
                        // StandardCopyOption.REPLACE_EXISTING -> indica que si existe se sobrescriba el contenido
                        Files.copy(p, destino, StandardCopyOption.REPLACE_EXISTING);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        } catch (IOException | RuntimeException e) {
            throw new ExamenSimpsonsException(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar el título y la fecha de salida del capítulo, solo
     * los que sean posteriores a 1992
     *
     * @throws ExamenSimpsonsException
     */
    public static void mostrarTituloYFechaMayorA1992() throws ExamenSimpsonsException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenSimpsons/simpsons.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList capitulos = doc.getElementsByTagName("capitulo");
            Pattern pattern = Pattern.compile("\\b([12]\\d{3})\\b");
            for (int i = 0; i < capitulos.getLength(); i++) {
                Element capitulo = (Element) capitulos.item(i);
                Matcher matcher = pattern.matcher(capitulo.getElementsByTagName("fecha_emision").item(0)
                        .getTextContent());
                if (matcher.find()) {
                    if (Integer.parseInt(matcher.group(1)) > 1992) {
                        System.out.println(capitulo.getElementsByTagName("nombre").item(0).getTextContent()
                                + " " + matcher.group(1));
                    }
                }
            }

        } catch (InvalidPathException | IOException | SAXException | ParserConfigurationException e) {
            throw new ExamenSimpsonsException(e.getMessage());
        }
    }

    /**
     * Este método va a crear un nuevo archivo XML solo con los capítulos que tengan una sinopsis
     * con más de 30 palabras
     *
     * @throws ExamenSimpsonsException
     */
    public static void nuevoXMLSinopsisMas30() throws ExamenSimpsonsException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenSimpsons/simpsons.xml");
            Path archivoNuevoXML = Path.of("Boletin_Tema6/src/Extra/ExamenSimpsons/simpsonsMas30.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList capitulos = doc.getDocumentElement().getElementsByTagName("capitulo");
            List<Node> capitulosMarcados = new ArrayList<>();
            for (int i = 0; i < capitulos.getLength(); i++) {
                Element capitulo = (Element) capitulos.item(i);
                String[] palabras = capitulo.getElementsByTagName("sinopsis").item(0)
                        .getTextContent().split("\\s+|\\n+");
                if (palabras.length <= 30) {
                    capitulosMarcados.add(capitulo);
                }
            }
            // Eliminamos los capítulos marcados
            capitulosMarcados.forEach(n -> doc.getDocumentElement().removeChild(n));
            // Guardamos los cambios
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoNuevoXML.toFile());
            TransformerFactory.newInstance().newTransformer().transform(domSource, streamResult);

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException |
                 TransformerException e) {
            throw new ExamenSimpsonsException(e.getMessage());
        }
    }

    /**
     * Este método va a crear un nuevo archivo XML donde los nombres de los personajes
     * principales dentro de la sinopsis irán dentro de **
     *
     * @throws ExamenSimpsonsException
     */
    public static void nuevoXMLSinopsisPersonajesPrincipales() throws ExamenSimpsonsException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenSimpsons/simpsons.xml");
            Path archivoNuevoXML = Path.of("Boletin_Tema6/src/Extra/ExamenSimpsons/simpsonsPrincipales.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList capitulos = doc.getDocumentElement().getElementsByTagName("capitulo");
            Pattern pattern = Pattern.compile("(Homer|Marge|Bart|Lisa|Maggie)");
            for (int i = 0; i < capitulos.getLength(); i++) {
                Element capitulo = (Element) capitulos.item(i);
                Element sinopsis = (Element) capitulo.getElementsByTagName("sinopsis").item(0);
                Matcher matcher = pattern.matcher(sinopsis.getTextContent());
                sinopsis.setTextContent(matcher.replaceAll(m -> "**" + m.group(1) + "**"));
            }
            // Guardamos los cambios
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoNuevoXML.toFile());
            TransformerFactory.newInstance().newTransformer().transform(domSource, streamResult);

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException
                 | TransformerException e) {
            throw new ExamenSimpsonsException(e.getMessage());
        }
    }
}