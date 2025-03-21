package Boletin2.Ordenador;

public class DiscoDuro {

    // Atributos
    private String marca;
    private String tipo;
    private int capacidad;

    // Generamos el constructor vacío
    public DiscoDuro() {
    }

    // Imprimimos todos los atributos de la clase 'DiscoDuro'
    @Override
    public String toString() {
        return "DiscoDuro{" +
                "marca='" + marca + '\'' +
                ", tipo='" + tipo + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }

    // Hacemos los set para poder darle valores al solicitárselos al usuario
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCapacidad() {
        return capacidad;
    }
}