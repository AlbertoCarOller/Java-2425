package Extra.ExamenPracticaV3;

import java.util.Objects;

public class Videojuego {
    // Creamos los atributos
    private String id;
    private String titulo;
    private String desarrolllador;
    private int anoLanzamiento;
    private String plataforma;
    private String genero;
    private String descripcion;

    // Creamos el constructor
    public Videojuego(String id, String titulo, String desarrolllador, int anoLanzamiento, String plataforma,
                      String genero, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.desarrolllador = desarrolllador;
        this.anoLanzamiento = anoLanzamiento;
        this.plataforma = plataforma;
        this.genero = genero;
        this.descripcion = descripcion;
    }

    // Hacemos los get
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDesarrolllador() {
        return desarrolllador;
    }

    public int getAnoLanzamiento() {
        return anoLanzamiento;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Videojuego that = (Videojuego) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Videojuego{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", desarrolllador='" + desarrolllador + '\'' +
                ", anoLanzamiento=" + anoLanzamiento +
                ", plataforma='" + plataforma + '\'' +
                ", genero='" + genero + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}