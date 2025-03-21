package Boletin1.Ejercicio8;

public enum Combustible {
    GASOLINA(3.5), DIESEL(2);

    // Guarcamos el precio añadido por día en esta variable
    private final double precioAnadido;

    // Hacemos el constructor para poder asignarle el precio por día
    Combustible(double precioAnadido) {
        this.precioAnadido = precioAnadido;
    }

    // Hacemos un get del precio
    public double getPrecioAnadido() {
        return precioAnadido;
    }
}