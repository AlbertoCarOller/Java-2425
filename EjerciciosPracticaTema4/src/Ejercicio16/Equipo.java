package Ejercicio16;

import java.util.Objects;

public class Equipo {

    // Creamos los atributos
    private int id;
    private static int idValor = 0;
    private String descripcion;
    private int valor;

    // Creamos el constructor
    public Equipo(String descripcion,int valor) {
        this.id = ++idValor;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    // Hacemos los get y set
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    protected void setValor(int valor) {
        this.valor = valor;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return id == equipo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Descripción: %s, Valor: %d", this.id, this.descripcion,this.valor);
    }
}