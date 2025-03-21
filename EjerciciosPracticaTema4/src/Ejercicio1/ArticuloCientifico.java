package Ejercicio1;

import java.net.MalformedURLException;
import java.net.URL;

public class ArticuloCientifico extends Documento implements LeibleOnline {

    // Creamos los atributos
    private int numCitas;
    private String revista;
    private int visualizaciones;
    private URL url;

    // Creamos el construtor
    public ArticuloCientifico(String titulo, String autor, int numCitas, String revista, int visualizaciones, String url) throws DocumentoExcepcion, MalformedURLException {
        super(titulo, autor);
        setNumCitas(numCitas);
        this.revista = revista;
        setVisualizaciones(visualizaciones);
        this.url = new URL(url);
    }

    // Creamos los get y set
    public int getNumCitas() {
        return numCitas;
    }

    public void setNumCitas(int numCitas) throws DocumentoExcepcion {
        if (numCitas < 1) {
            throw new DocumentoExcepcion("El número de citas no puede ser menor a 1");
        }
        this.numCitas = numCitas;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    // Implementamos los métodos de las interfaces
    @Override
    public String conocerUrl() {
        return this.url.toString();
    }

    @Override
    public int obtenerVisualizaciones() {
        return this.visualizaciones;
    }

    public int getVisualizaciones() {
        return visualizaciones;
    }

    private void setVisualizaciones(int visualizaciones) throws DocumentoExcepcion {
        if (visualizaciones < 0) {
            throw new DocumentoExcepcion("Las visualizaciones no pueden ser menor a 0");
        }
        this.visualizaciones = visualizaciones;
    }

    public URL getUrl() {
        return url;
    }

    private void setUrl(URL url) {
        this.url = url;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Documento{" +
                "titulo='" + super.getTitulo() + '\'' +
                ", autor='" + super.getAutor() + '\'' +
                ", id=" + super.getId() +
                ", numCitas=" + this.numCitas +
                ", revista=" + this.revista +
                ", visualizaciones=" + this.visualizaciones +
                ", url=" + this.url +
                '}';
    }
}