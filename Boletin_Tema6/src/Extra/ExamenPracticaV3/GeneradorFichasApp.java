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
                String id = videojuego.getAttribute("id");
                if (id.isEmpty()) {
                    System.out.println("El videojuego " + (i + 1) + " no tiene id");
                    continue;
                }
                Node titulo = null;
                NodeList tituloList = videojuego.getElementsByTagName("titulo");
                if (tituloList.getLength() < 1) {
                    System.out.println("El videojuego " + (i + 1) + " no tiene título");
                    continue;

                } else {
                    titulo = tituloList.item(0);
                }
                Node desarrollador = null;
                NodeList desarrolladorList = videojuego.getElementsByTagName("desarrollador");
                if (desarrolladorList.getLength() < 1) {
                    System.out.println("El videojuego " + (i + 1) + " no tiene desarrollador");
                    continue;

                } else {
                    desarrollador = desarrolladorList.item(0);
                }
                Node lanzamiento = null;
                NodeList lanzamientoList = videojuego.getElementsByTagName("lanzamiento");
                if (lanzamientoList.getLength() < 1) {
                    System.out.println("El videojuego " + (i + 1) + " no tiene lanzamiento");
                    continue;

                } else {
                    lanzamiento = lanzamientoList.item(0);
                }
                Node plataforma = null;
                NodeList plataformaList = videojuego.getElementsByTagName("plataforma");
                if (plataformaList.getLength() < 1) {
                    System.out.println("El videojuego " + (i + 1) + " no tiene plataformas");
                    continue;

                } else {
                    plataforma = plataformaList.item(0);
                }
                Node genero = null;
                NodeList generoList = videojuego.getElementsByTagName("genero");
                if (generoList.getLength() < 1) {
                    System.out.println("El videojuego " + (i + 1) + " no tiene genero");
                    continue;

                } else {
                    genero = generoList.item(0);
                }
                String descripcion = "";
                NodeList descripcionList = videojuego.getElementsByTagName("descripcion");
                if (descripcionList.getLength() < 1) {
                    System.out.println("El videojuego " + (i + 1) + " no tiene descripcion");

                } else {
                    descripcion = descripcionList.item(0).getTextContent();
                }
                videojuegosList.add(new Videojuego(id, titulo.getTextContent(), desarrollador.getTextContent(),
                        Integer.parseInt(lanzamiento.getTextContent()), plataforma.getTextContent(), genero.getTextContent(),
                        descripcion));
            }
            return videojuegosList;

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException e) {
            throw new GeneradorFichasException(e.getMessage());
        }
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
            if (matcher.find()) {
                System.out.println("Videojuego: " + v.getTitulo() + " PEGI: " + matcher.group(1));

            } else {
                System.out.println("El videojuego " + v.getTitulo() + " no tiene PEGI");
            }
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
     * @param fichas una lista con todas las fichas
     */
    public static void mostrarInformacionPorConsola(List<Videojuego> videojuegos, List<Path> fichas) {
        System.out.println("Número total de videojuegos: " + videojuegos.size());
        System.out.println("Número total de fichas: " + fichas.size());
        System.out.println("Nombres de ficheros generados: ");
        fichas.forEach(p -> System.out.println(p.toFile().getName()));
    }
    // TODO: optimizar el código
}