package Extra.Ejercicio14;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Paquete implements Comparable<Paquete> {

    // Creamos los atributos
    private int numeroSeguimiento;
    private static int valorNumSeguimiento = 0;
    private String nombreRemitente;
    private String nombreDestinatario;
    private int prioridad;
    private LocalDateTime fechaRecepcion;

    // Creamos el constructor
    public Paquete(String nombreRemitente, String nombreDestinatario, int prioridad, LocalDateTime fechaRecepcion) throws PaqueteException {
        this.numeroSeguimiento = ++valorNumSeguimiento;
        setNombreRemitente(nombreRemitente);
        setNombreDestinatario(nombreDestinatario);
        setPrioridad(prioridad);
        setFechaRecepcion(fechaRecepcion);
    }

    // Hacemos un get y set
    public int getNumeroSeguimiento() {
        return numeroSeguimiento;
    }

    public void setNumeroSeguimiento(int numeroSeguimiento) {
        this.numeroSeguimiento = numeroSeguimiento;
    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public LocalDateTime getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDateTime fechaRecepcion) throws PaqueteException {
        if (fechaRecepcion.isAfter(LocalDateTime.now())) {
            throw new PaqueteException("La fecha no puede ser posterior a la actual");
        }
        this.fechaRecepcion = fechaRecepcion;
    }

    public void setNombreRemitente(String nombreRemitente) throws PaqueteException {
        if (!Character.isUpperCase(nombreRemitente.charAt(0))) {
            throw new PaqueteException("La primera letra debe estar en mayúsculas");
        }
        for (int i = 1; i < nombreRemitente.length(); i++) {
            if (Character.isUpperCase(nombreRemitente.charAt(i)) || !Character.isLetter(nombreRemitente.charAt(i))) {
                throw new PaqueteException("El resto de letras debe estar en minúsculas y ser letras");
            }
        }
        this.nombreRemitente = nombreRemitente;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) throws PaqueteException {
        if (!Character.isUpperCase(nombreDestinatario.charAt(0))) {
            throw new PaqueteException("La primera letra debe estar en mayúsculas");
        }
        for (int i = 1; i < nombreDestinatario.length(); i++) {
            if (Character.isUpperCase(nombreDestinatario.charAt(i)) || !Character.isLetter(nombreDestinatario.charAt(i))) {
                throw new PaqueteException("El resto de letras debe estar en minúsculas y ser letras");
            }
        }
        this.nombreDestinatario = nombreDestinatario;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) throws PaqueteException {
        if (prioridad < 1 || prioridad > 3) {
            throw new PaqueteException("La prioridad tiene que estar entre 1 y 3");
        }
        this.prioridad = prioridad;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paquete paquete = (Paquete) o;
        return numeroSeguimiento == paquete.numeroSeguimiento;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroSeguimiento);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Número de seguimiento: %d, Nombre remitente: %s, Nombre destinatario: %s, Prioridad: %d" +
                        ", Tiempo en espera: %d horas", this.numeroSeguimiento, this.nombreRemitente,
                this.nombreDestinatario, this.prioridad, Duration.between(this.fechaRecepcion, LocalDateTime.now()).toHours());
    }

    @Override
    public int compareTo(Paquete o) {
        int i = Integer.compare(this.prioridad, o.prioridad);
        if (i == 0) {
            return fechaRecepcion.compareTo(o.fechaRecepcion);
        }
        return i;
    }
}