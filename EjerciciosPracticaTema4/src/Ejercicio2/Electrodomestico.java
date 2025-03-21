package Ejercicio2;

public abstract class Electrodomestico {

    // Creamos los atributos
    private String nombre;
    private String marca;
    private double precio;

    // Creamos el constructor
    public Electrodomestico(String nombre, String marca, double precio) throws ElectrodomesticoException {
        this.nombre = nombre;
        this.marca = marca;
        setPrecio(precio);
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    private void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    private void setPrecio(double precio) throws ElectrodomesticoException {
        if (precio < 0) {
            throw new ElectrodomesticoException("El precio no puede ser menor que 0");
        }
        this.precio = precio;
    }
}