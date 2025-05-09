package Extra.ExamenPracticaV3;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneradorFichasApp {
    public static void main(String[] args) {
        try {
            List<Videojuego> videojuegos = cargarVideojuegosEnLista();
            mostrarPegi(videojuegos);
            List<Path> fichas = formateoYCreacionDeFichas(videojuegos);
            mostrarInformacionPorConsola(videojuegos, fichas);

        } catch (GeneradorFichasException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a transformar los elementos videojuegos del archivo XML a
     * objetos videojuegos y los añadirá a una lista, los que sean válidos
     *
     * @return una lista de videojuegos válidos
     * @throws GeneradorFichasException
     */
    public static List<Videojuego> cargarVideojuegosEnLista() throws GeneradorFichasException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV3/catalogo_videojuegos.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList videojuegos = doc.getDocumentElement().getElementsByTagName("videojuego");
            List<Videojuego> videojuegosList = new ArrayList<>();
            for (int i = 0; i < videojuegos.getLength(); i++) {
                Element videojuego = (Element) videojuegos.item(i);
                Videojuego videojuegoCreado = crearVideojuego(videojuego, i);
                if (videojuegoCreado != null) {
                    videojuegosList.add(videojuegoCreado);
                }
            }
            return videojuegosList;

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException e) {
            throw new GeneradorFichasException(e.getMessage());
        }
    }

    /**
     * Transforma el Element que se le pasa por parámetros a un objeto
     *
     * @param videojuegoE el Element videojuego a transformar
     * @param i           el índice del for
     * @return el objeto Videojuego
     */
    public static Videojuego crearVideojuego(Element videojuegoE, int i) {
        String id = videojuegoE.getAttribute("id");
        if (id.isEmpty()) {
            System.out.println("El videojuego " + (i + 1) + " no tiene id");
            return null;
        }
        Node titulo = videojuegoE.getElementsByTagName("titulo").item(0);
        if (titulo == null) {
            System.out.println("El videojuego " + (i + 1) + " no tiene título");
            return null;
        }
        Node desarrollador = videojuegoE.getElementsByTagName("desarrollador").item(0);
        if (desarrollador == null) {
            System.out.println("El videojuego " + (i + 1) + " no tiene desarrollador");
            return null;
        }
        Node lanzamiento = videojuegoE.getElementsByTagName("lanzamiento").item(0);
        if (lanzamiento == null) {
            System.out.println("El videojuego " + (i + 1) + " no tiene lanzamiento");
            return null;
        }
        Node plataforma = videojuegoE.getElementsByTagName("plataforma").item(0);
        if (plataforma == null) {
            System.out.println("El videojuego " + (i + 1) + " no tiene plataformas");
            return null;
        }
        Node genero = videojuegoE.getElementsByTagName("genero").item(0);
        if (genero == null) {
            System.out.println("El videojuego " + (i + 1) + " no tiene genero");
            return null;
        }
        String descripcionE = "";
        Node descripcion = videojuegoE.getElementsByTagName("descripcion").item(0);
        if (descripcion == null) {
            System.out.println("El videojuego " + (i + 1) + " no tiene descripción");

        } else {
            descripcionE = descripcion.getTextContent();
        }
        return new Videojuego(id, titulo.getTextContent(), desarrollador.getTextContent(),
                Integer.parseInt(lanzamiento.getTextContent()), plataforma.getTextContent(),
                genero.getTextContent(), descripcionE);
    }

    /**
     * Este método va a mostrar por pantalla los PEGI de cada videojuego
     * en caso de que tuvieran, en caso de que no se mostrará por consola
     *
     * @throws GeneradorFichasException
     */
    public static void mostrarPegi(List<Videojuego> videojuegos) throws GeneradorFichasException {
        Pattern pattern = Pattern.compile("\\bPEGI:\\s?(\\d{1,2})\\b");
        videojuegos.forEach(v -> {
            Matcher matcher = pattern.matcher(v.getDescripcion());
            String s = matcher.find() ? "Videojuego: " + v.getTitulo() + " PEGI: " + matcher.group(1)
                    : "El videojuego " + v.getTitulo() + " no tiene PEGI";
            System.out.println(s);
        });
    }

    /**
     * Este método va a crear un fichero por cada videojuego con el contenido
     * correspondiente y va a ir añadiendo cada Path en una lista para su posterior
     * uso
     *
     * @return una lista de los Path de las fichas de los videojuegos
     * @throws GeneradorFichasException
     */
    public static List<Path> formateoYCreacionDeFichas(List<Videojuego> videojuegos) throws GeneradorFichasException {
        Pattern pattern = Pattern.compile("[\\s[^\\p{Alnum}]]");
        Pattern pattern1 = Pattern.compile("\\bPEGI:\\s?(\\d{1,2})\\b");
        List<Path> fichasVideojuegos = new ArrayList<>();
        videojuegos.forEach(v -> {
            Matcher matcher = pattern.matcher(v.getTitulo());
            Matcher matcher1 = pattern1.matcher(v.getDescripcion());
            // Creamos el Path de la ficha del videojuego
            Path fichaVideojuego = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV3")
                    .resolve(matcher.replaceAll(m -> "_").trim().toLowerCase().concat("_")
                            .concat(v.getId()).concat(".txt"));
            // Creamos el contenido de la ficha
            try (PrintWriter pw = new PrintWriter(new FileWriter(fichaVideojuego.toFile()))) {
                pw.println("=====================");
                pw.println("ficha del videojuego".toUpperCase());
                pw.println("=====================");
                pw.println("ID: " + v.getId());
                pw.println("Título: " + v.getTitulo());
                pw.println("Desarrollador: " + v.getDesarrolllador());
                pw.println("Año de lanzamiento: " + v.getAnoLanzamiento());
                pw.println("Género: " + v.getGenero());
                pw.println("Plataformas: " + v.getPlataforma());
                pw.println("PEGI: " + (matcher1.find() ? matcher1.group(1) : "No tiene PEGI"));
                pw.println("----------------");
                pw.println("Descripción: ");
                pw.println((!v.getDescripcion().isEmpty() ? v.getDescripcion().trim() : "No tiene descripción"));

            } catch (IOException e) {
            }
            // Añadimos la ficha a una lista
            fichasVideojuegos.add(fichaVideojuego);
        });
        return fichasVideojuegos;
    }

    /**
     * Muestra por pantalla la información básica especificada
     *
     * @param videojuegos una lista con todos los videojuegos
     * @param fichas      una lista con todas las fichas
     */
    public static void mostrarInformacionPorConsola(List<Videojuego> videojuegos, List<Path> fichas) {
        System.out.println("Número total de videojuegos: " + videojuegos.size());
        System.out.println("Número total de fichas: " + fichas.size());
        System.out.println("Nombres de ficheros generados: ");
        fichas.forEach(p -> System.out.println(p.toFile().getName()));
    }
}