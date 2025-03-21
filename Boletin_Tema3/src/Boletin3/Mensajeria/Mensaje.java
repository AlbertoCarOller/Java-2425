package Boletin3.Mensajeria;

import java.time.LocalDate;

public class Mensaje {

    // Atributos
    private String asunto;
    private String cuerpo;
    private LocalDate fechaEnvio;
    private Persona remitente;
    private Persona destinatario;

    // Creamos el constructor
    public Mensaje(String asunto, String cuerpo, Persona remitente, Persona destinatario) {
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.fechaEnvio = LocalDate.now();
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    // Hacemos los set
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public void setDestinatario(Persona destinatario) {
        this.destinatario = destinatario;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public void setRemitente(Persona remitente) {
        this.remitente = remitente;
    }

    // Hacemos los get
    public String getAsunto() {
        return asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public Persona getRemitente() {
        return remitente;
    }

    public Persona getDestinatario() {
        return destinatario;
    }
}