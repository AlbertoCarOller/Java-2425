package Boletin2.Producto;

public class Producto {

    // Creamos los atributos
    private int codigo;
    private String descripcion;
    private double precio;

    // Creamos el IVA (constante)
    private static final int IVA = 20;

    // Creamos el constructor donde a la hora de crear el objeto le asignamos un código aleatorio
    public Producto() {

        this.codigo = generarCodigoAleatorio();
    }

    // Hacemos los get para acceder a los atributos
    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    // Hacemos los set para modificar el valor de los atributos
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) throws ProductoExcepcion {

        if (precio < 0) {

            throw new ProductoExcepcion("El precio del producto no puede ser negativo");
        }

        this.precio = precio;
    }

    // Vamos a crear un método para generar un código aleatorio, se puede hacer incrementando los id, sin este método
    private int generarCodigoAleatorio() {

        // Generamos un número entre 1000 y 9999
        return (int) (Math.random() * 9000) + 1000;
    }

    // Hacemos el método para calcular el precio del producto con el IVA
    public void calcularIva() {

        double iva = (precio * IVA) / 100;
        this.precio += iva;
    }
}