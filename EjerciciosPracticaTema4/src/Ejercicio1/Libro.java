package Ejercicio1;

import java.net.MalformedURLException;
import java.net.URL;

public class Libro extends Documento implements Descargable {

    // Creamos los atributos
    private static final int TAMANO_PAGINA = 2048;
    private int numPaginas;
    private String editorial;
    private int descargas;

    // Creamos el constructor
    public Libro(String titulo, String autor, int numPaginas, String editorial, int descargas) throws DocumentoExcepcion, MalformedURLException {
        super(titulo, autor);
        setNumPaginas(numPaginas);
        this.editorial = editorial;
        setDescargas(descargas);
    }

    // Creamos los get y set
    public int getNumPaginas() {
        return numPaginas;
    }

    private void setNumPaginas(int numPaginas) throws DocumentoExcepcion {
        if (numPaginas < 1) {
            throw new DocumentoExcepcion("Un libro no puede tener menos de 1 página");
        }
        this.numPaginas = numPaginas;
    }

    public String getEditorial() {
        return editorial;
    }

    private void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getDescargas() {
        return descargas;
    }

    private void setDescargas(int descargas) throws DocumentoExcepcion {
        if (descargas < 0) {
            throw new DocumentoExcepcion("Las descargas no pueden ser menor a 0");
        }
        this.descargas = descargas;
    }

    // Implementamos los métodos
    @Override
    public int obtenerTam() {
        return this.numPaginas * TAMANO_PAGINA;
    }

    @Override
    public int cantDescargas() {
        return this.descargas;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Documento{" +
                "titulo='" + super.getTitulo() + '\'' +
                ", autor='" + super.getAutor() + '\'' +
                ", id=" + super.getId() +
                ", numPaginas=" + this.numPaginas +
                ", editorial=" + this.editorial +
                ", descargas=" + this.descargas +
                '}';
    }
}