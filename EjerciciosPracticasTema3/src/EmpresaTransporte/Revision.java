package EmpresaTransporte;

import java.time.LocalDate;
import java.time.Period;

public class Revision {

    // Creamos los atributos
    private LocalDate fecha;
    private boolean pasada;
    Period periodoValidez;

    // Creamos el constructor
    public Revision(LocalDate fecha, boolean pasada, Period periodoValidez) {
        this.fecha = fecha;
        this.pasada = pasada;
        this.periodoValidez = periodoValidez;
    }

    // Hacemos los get y set
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isPasada() {
        return pasada;
    }

    public void setPasada(boolean pasada) {
        this.pasada = pasada;
    }

    public Period getPeriodoValidez() {
        return periodoValidez;
    }

    public void setPeriodoValidez(Period periodoValidez) {
        this.periodoValidez = periodoValidez;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Revision{" +
                "fecha=" + fecha +
                ", pasada=" + pasada +
                ", periodoValidez=" + periodoValidez +
                '}';
    }
}