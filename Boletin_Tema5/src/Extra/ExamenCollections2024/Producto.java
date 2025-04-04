package Extra.ExamenCollections2024;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Producto {
    private String marca;
    private String modelo;
    private String descripcion;
    private double precio;
    private LocalDate fechaIncorporacion;
    private int stock;

    // Hacemos el constructor
    public Producto(String marca, String modelo, String descripcion, double precio, int stock) {
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaIncorporacion = LocalDate.now();
        this.stock = stock;
    }

    // Hacemos los get
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalDate getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public int getStock() {
        return stock;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(marca, producto.marca) && Objects.equals(modelo, producto.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marca, modelo);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Marca: %s, Modelo: %s, Descripción: %s, Precio: %f, Fecha incorporación: %s, Stock: %s",
                this.marca, this.modelo, this.descripcion, this.precio,
                this.fechaIncorporacion.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), this.stock);
    }
}
