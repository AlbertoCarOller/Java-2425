package Ejercicio1;

import java.util.Objects;

public abstract class Documento {

    // Creamos los atributos
    private String titulo;
    private String autor;
    private int id;
    // Creamos una variable estática que se incrementará en uno cada vez que se cree un nuevo documento
    private static int contadorID;

    // Creamos el constructor
    public Documento(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.id = contadorID++;
    }

    // Creamos los get y los set
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Documento{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return Objects.equals(titulo, documento.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }
}