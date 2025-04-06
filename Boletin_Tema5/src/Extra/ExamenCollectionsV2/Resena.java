package Extra.ExamenCollectionsV2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Resena {

    // Creamos los atributos
    private String usuario;
    private int valoracion;
    private String comentario;
    private LocalDate fechaPublicacion;

    // Creamos el constructor
    public Resena(String usuario, int valoracion, String comentario) throws PeliculaException {
        this.usuario = usuario.trim();
        setValoracion(valoracion);
        this.comentario = comentario.trim();
        this.fechaPublicacion = LocalDate.now();
    }

    // Creamos los get y set
    public String getUsuario() {
        return usuario;
    }

    public int getValoracion() {
        return valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    private void setFechaPublicacion(LocalDate fechaPublicacion) throws PeliculaException {
        if (fechaPublicacion.isAfter(LocalDate.now())) {
            throw new PeliculaException("La fecha de la publicación no debe ser posterior");
        }
        this.fechaPublicacion = fechaPublicacion;
    }

    private void setValoracion(int valoracion) throws PeliculaException {
        if (valoracion < 1 || valoracion > 10) {
            throw new PeliculaException("La valoración debe estar entre 1 y 10");
        }
        this.valoracion = valoracion;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Usuario: %s, Valoración: %d, Comentario: %s, Fecha publicación: %s",
                this.usuario, this.valoracion, this.comentario,
                this.fechaPublicacion.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }
}