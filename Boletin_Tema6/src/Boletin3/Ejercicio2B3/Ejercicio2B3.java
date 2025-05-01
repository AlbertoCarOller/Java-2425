package Boletin3.Ejercicio2B3;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio2B3 {
    public static void main(String[] args) {
        try {
            System.out.println(contarElementosConMenuClass());
            mostrarTitulo();
            System.out.println("Número de div: " + contarDiv());
            System.out.println("Número de div con id valor: " + contarDivConValor());
            mostrarTextoAlternativaImg();
            mostrarTitularSeguidoDeAlt();
            mostrarOpcionesMenu();
            mostrarTitularesSeguidoDelParrafo();

        } catch (Ejercicio2B3Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a crear un DocumentBuilder para así facilitar esta parte del código
     * ya que siempre es la misma
     *
     * @return el DocumentBuilder
     * @throws Ejercicio2B3Exception
     */
    public static DocumentBuilder crearDocumentBuilder() throws Ejercicio2B3Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            return documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            throw new Ejercicio2B3Exception(e.getMessage());
        }
    }

    /**
     * Este método cuenta los elementos que tengan un atributo class que coincida
     * con el contenido del regex
     *
     * @return
     * @throws Ejercicio2B3Exception
     */
    public static int contarElementosConMenuClass() throws Ejercicio2B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio2B3/web1.html");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            /* \\b -> Marca la frontera de la palabra, en \\b puede haber espacios, puntos, comas o
             * simplemente el principio y fin, con un caracter de palabra no hay frontera, ya que es
             * del mismo tipo, los números no hacen frontera.
             * (?i) -> Puede estar en mayúsculas o minúsculas. Otra forma: Pattern.CASE_INSENSITIVE */
            Pattern pattern = Pattern.compile("(?i)\\bmenu\\b");
            return elementosConAtributoClassRecursivo(doc.getDocumentElement(), pattern, 0);

        } catch (InvalidPathException | SAXException | IOException e) {
            throw new Ejercicio2B3Exception(e.getMessage());
        }
    }

    /**
     * Este método es auxiliar del anterior, este realmente es el que comprueba y lleva
     * la cuenta de las veces que aparece el regex dentro del contenido del atributo,
     * se llama recursivamente, ya que si hay un nodo que tenga hijos, pues se mirará
     * dentro de esta
     *
     * @param node
     * @param pattern
     * @param num
     * @return
     */
    public static int elementosConAtributoClassRecursivo(Node node, Pattern pattern, int num) {
        NodeList nodeList = node.getChildNodes();
        // Recorremos los nodos hijos
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeTemporal = nodeList.item(i);
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                /* Si el nodo actual tiene hijos buscamos en los distintos nodos, una vez terminada esta
                 * llamada al mismo método, si ya ha mirado los hijos, ahora se mirará a él mismo */
                if (node.hasChildNodes()) {
                    // Llamamos al método y se actualiza "num"
                    num = elementosConAtributoClassRecursivo(nodeTemporal, pattern, num);
                }
                /* Obtenemos el atributo "class" en caso de que exista del nodo actual, lo transformamos en "Element"
                 * para poder acceder al atributo */
                String clases = ((Element) nodeTemporal).getAttribute("class");
                // Separamos el String para mirar cada parte de este en busca de menu
                String[] clasesSueltas = clases.split("\\s+");
                for (int j = 0; j < clasesSueltas.length; j++) {
                    Matcher matcher = pattern.matcher(clasesSueltas[j]);
                    /* Si se ha encontrado se suma 1 a "num", si se repite en el mismo valor del atributo la palabra
                     * se sumará también en este caso */
                    if (matcher.matches()) {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    /**
     * Este método mostrará el título del archivo html
     *
     * @throws Ejercicio2B3Exception
     */
    public static void mostrarTitulo() throws Ejercicio2B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio2B3/web1.html");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            System.out.println(((Element) doc.getDocumentElement().getElementsByTagName("head").item(0))
                    .getElementsByTagName("title").item(0).getTextContent());

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio2B3Exception(e.getMessage());
        }
    }

    /**
     * Este método cuenta los elementos "div" que hay en el Document
     *
     * @return
     * @throws Ejercicio2B3Exception
     */
    public static int contarDiv() throws Ejercicio2B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio2B3/web1.html");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            // .getElementsByTagName() da todos los elementos, mirando todos los niveles a partir del nivel actual
            return doc.getDocumentElement().getElementsByTagName("div").getLength();

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio2B3Exception(e.getMessage());
        }
    }

    /**
     * Este método va a contar los div que tengan un atributo "id" con
     * valor
     *
     * @return el número de divisores
     * @throws Ejercicio2B3Exception
     */
    public static int contarDivConValor() throws Ejercicio2B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio2B3/web1.html");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            Node root = doc.getDocumentElement();
            return contarDivRecursivo(root, 0);

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio2B3Exception(e.getMessage());
        }
    }

    /**
     * Este método es recursivo, ya que si el nodo actual tiene hijos entra dentro
     * en busca de "div" que no estén vacíos
     *
     * @param root
     * @param num
     * @return
     */
    public static int contarDivRecursivo(Node root, int num) {
        NodeList hijos = root.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node nodoTemporal = hijos.item(i);
            if (nodoTemporal.hasChildNodes()) {
                num = contarDivRecursivo(nodoTemporal, num);
            }
            if (nodoTemporal.getNodeName().equals("div") && !((Element) nodoTemporal).getAttribute("id").isEmpty()) {
                num++;
            }
        }
        return num;
    }

    /**
     * Este método va a mostrar el texto alternativo a las imágenes
     *
     * @throws Ejercicio2B3Exception
     */
    public static void mostrarTextoAlternativaImg() throws Ejercicio2B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio2B3/web1.html");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList img = doc.getDocumentElement().getElementsByTagName("img");
            for (int i = 0; i < img.getLength(); i++) {
                System.out.println("alt=" + ((Element) img.item(i)).getAttribute("alt"));
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio2B3Exception(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar los titulares seguido de los textos
     * alternativos a las imágenes
     *
     * @throws Ejercicio2B3Exception
     */
    public static void mostrarTitularSeguidoDeAlt() throws Ejercicio2B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio2B3/web1.html");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            mostrarTitularSeguidoDeAltRecursivo(doc.getDocumentElement());

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio2B3Exception(e.getMessage());
        }
    }

    /**
     * Este método es recursivo y es un auxiliar del método anterior
     *
     * @param root
     */
    public static void mostrarTitularSeguidoDeAltRecursivo(Node root) {
        NodeList hijos = root.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node nodoTemporal = hijos.item(i);
            if (nodoTemporal.getNodeType() == Node.ELEMENT_NODE) {
                if (nodoTemporal.hasChildNodes()) {
                    mostrarTitularSeguidoDeAltRecursivo(nodoTemporal);
                }
                Element element = (Element) nodoTemporal;
                if (element.getNodeName().equals("h2")) {
                    System.out.println("H2: " + element.getTextContent());
                }
                if (element.getNodeName().equals("img")) {
                    System.out.println("Alternativo: " + element.getAttribute("alt"));
                    System.out.println("----------------------------");
                }
            }
        }
    }

    /**
     * Este método va a recorrer todas las opciones del menú
     * principal mostrándolas por pantalla
     *
     * @throws Ejercicio2B3Exception
     */
    public static void mostrarOpcionesMenu() throws Ejercicio2B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio2B3/web1.html");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList nodeList = doc.getElementsByTagName("div");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element.getAttribute("id").equals("menu-principal")) {
                    NodeList opciones = element.getElementsByTagName("li");
                    for (int j = 0; j < opciones.getLength(); j++) {
                        System.out.println(opciones.item(j).getTextContent());
                    }
                    break;
                }
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio2B3Exception(e.getMessage());
        }
    }

    /**
     * Este método va a imprimir por pantalla el titular y párrafo de las
     * noticias
     *
     * @throws Ejercicio2B3Exception
     */
    public static void mostrarTitularesSeguidoDelParrafo() throws Ejercicio2B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio2B3/web1.html");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList divList = doc.getDocumentElement().getElementsByTagName("div");
            for (int i = 0; i < divList.getLength(); i++) {
                if (divList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element div = (Element) divList.item(i);
                    if (div.getAttribute("class").equals("noticia")) {
                        NodeList titular = div.getElementsByTagName("h2");
                        if (titular.getLength() != 0) {
                            System.out.println("Titular: " + titular.item(0).getTextContent());
                        }
                        NodeList parrafo = div.getElementsByTagName("p");
                        if (parrafo.getLength() != 0) {
                            System.out.println("Párrafo: " + parrafo.item(0).getTextContent());
                        }
                        System.out.println();
                    }
                }
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio2B3Exception(e.getMessage());
        }
    }
}