package Ejercicio4;

import java.time.LocalDate;
import java.time.Year;

public class ImagenDigital extends Obra implements Exibible {

    // Creamos los atributos
    private int resolucion;
    private Formato formato;
    private LocalDate fechaFinExibicion;
    private int numAccesosPermitidos;
    private int visitasRegistradas;

    // Hacemos el constructor
    public ImagenDigital(String titulo, String autor, Year anoCreacion, double valorEuros, int resolucion, Formato formato,
                         LocalDate fechaFinExibicion, int numAccesosPermitidos, int visitasRegistradas) throws ObraException {
        super(titulo, autor, anoCreacion, valorEuros);
        setResolucion(resolucion);
        this.formato = formato;
        this.fechaFinExibicion = fechaFinExibicion;
        setNumAccesosPermitidos(numAccesosPermitidos);
        setVisitasRegistradas(visitasRegistradas);
    }

    // Creamos los get y set
    public int getResolucion() {
        return resolucion;
    }

    public void setResolucion(int resolucion) throws ObraException {
        if (resolucion < 144) {
            throw new ObraException("La resolución no puede ser menor a 144p");
        }
        this.resolucion = resolucion;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public LocalDate getFechaFinExibicion() {
        return fechaFinExibicion;
    }

    private void setFechaFinExibicion(LocalDate fechaFinExibicion) {
        this.fechaFinExibicion = fechaFinExibicion;
    }

    public int getNumAccesosPermitidos() {
        return numAccesosPermitidos;
    }

    public int getVisitasRegistradas() {
        return visitasRegistradas;
    }

    private void setVisitasRegistradas(int visitasRegistradas) throws ObraException {
        if (visitasRegistradas < 0) {
            throw new ObraException("No puede tener menos de 0 visitas");
        }
        this.visitasRegistradas = visitasRegistradas;
    }

    private void setNumAccesosPermitidos(int numAccesosPermitidos) throws ObraException {
        if (numAccesosPermitidos < 0) {
            throw new ObraException("No puede haber accesos negativos");
        }
        this.numAccesosPermitidos = numAccesosPermitidos;
    }

    @Override
    public void finalizaExibicion() {
        System.out.println("La fecha de la exbición es " + this.fechaFinExibicion);
    }

    @Override
    public void numAccesos() {
        System.out.println("El número de accesos permitidos es de " + this.numAccesosPermitidos);
    }

    @Override
    public void verVisitas() throws ObraException {
        if (this.visitasRegistradas > this.numAccesosPermitidos) {
            throw new ObraException("No puede haber más visitas que accesos");
        }
        System.out.println("El número de visitas es de " + this.visitasRegistradas);
    }
}