package Avion;

import java.time.LocalDate;
import java.time.Period;

public class Revision {

    // Creamos los atributos
    private LocalDate fecha;
    private String estado;
    private Period periodoValidez;

    // Hacemos el constructor
    public Revision(LocalDate fecha, String estado, Period periodoValidez) throws AvionExcepcion {
        this.fecha = fecha;
        setEstado(estado);
        this.periodoValidez = periodoValidez;
    }

    // Hacemos los get y set
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) throws AvionExcepcion {

        if (!estado.equalsIgnoreCase("correcto") && !estado.equalsIgnoreCase("incorrecto")) {
            throw new AvionExcepcion("El estado no es válido");
        }
        this.estado = estado;
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
                ", estado='" + estado + '\'' +
                ", periodoValidez=" + periodoValidez +
                '}';
    }
}