package Extra.ExamenCollectionsV4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ValoracionV2 {
    private String usuario;
    private int puntuacion;
    private String comentario;
    private LocalDate fecha;

    public ValoracionV2(String usuario, int puntuacion, String comentario) {
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = LocalDate.now();
    }

    public String getUsuario() {
        return usuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValoracionV2 that = (ValoracionV2) o;
        return Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(usuario);
    }

    @Override
    public String toString() {
        return String.format("Usuario: %s, Puntuación: %d, Comentario: %s, Fecha: %s", this.usuario, this.puntuacion,
                this.comentario, this.fecha.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }
}