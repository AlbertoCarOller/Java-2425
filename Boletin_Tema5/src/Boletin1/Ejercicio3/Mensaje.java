package Boletin1.Ejercicio3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensaje implements Comparable<Mensaje> {

    // Creamos los atributos
    private Persona remitente;
    private String mensaje;
    private LocalDateTime fechaYHora;

    // Creamos el constructor
    public Mensaje(Persona remitente, String mensaje) {
        this.remitente = remitente;
        this.mensaje = mensaje;
        this.fechaYHora = LocalDateTime.now();
    }

    // Creamos los get y set
    public Persona getRemitente() {
        return remitente;
    }

    private void setRemitente(Persona remitente) {
        this.remitente = remitente;
    }

    public String getMensaje() {
        return mensaje;
    }

    private void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    private void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        /* Hacemos un String.formar() que va a funcionar como un printf, formateamos la fecha y hora para que nos lo dé
         con un formato personalizado */
        return String.format("De: %s\nTexto: %s\nFecha y hora: %s\n", this.remitente.getNombre(), this.mensaje,
                this.fechaYHora.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")));
    }

    // Implementamos el método de la interfaz
    @Override
    public int compareTo(Mensaje o) {
        return this.remitente.getNombre().compareTo(o.remitente.getNombre());
    }
}