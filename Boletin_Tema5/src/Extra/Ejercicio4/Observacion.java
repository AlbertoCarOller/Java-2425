package Extra.Ejercicio4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Observacion implements Comparable<Observacion> {

    // Creamos los atributos
    private LocalDate fecha;
    private String nombreArqueologo;

    // Hacemos el constructor
    public Observacion(LocalDate fecha, String nombreArqueologo) throws HallazgoException {
        setFecha(fecha);
        this.nombreArqueologo = nombreArqueologo;
    }

    // Hacemos los get y set
    public LocalDate getFecha() {
        return fecha;
    }

    private void setFecha(LocalDate fecha) throws HallazgoException {
        if (fecha.isAfter(LocalDate.now())) {
            throw new HallazgoException("La fecha no puede ser mayor a la fecha actual");
        }
        this.fecha = fecha;
    }

    public String getNombreArqueologo() {
        return nombreArqueologo;
    }

    private void setNombreArqueologo(String nombreArqueologo) {
        this.nombreArqueologo = nombreArqueologo;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Fecha: %s, Nombre arqueólogo: %s", this.fecha.format(DateTimeFormatter.ofPattern("yy/MM/dd")),
                this.nombreArqueologo);
    }

    // Implementamos el compareTo
    @Override
    public int compareTo(Observacion o) {
        return this.fecha.compareTo(o.fecha);
    }
}