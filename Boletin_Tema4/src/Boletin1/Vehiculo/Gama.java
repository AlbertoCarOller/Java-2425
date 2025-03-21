package Boletin1.Vehiculo;

public enum Gama {
    ALTA(50), MEDIA(40), BAJA(30);

    // Creamos el atributo precio que va a almacenar este valor
    private final double precioBase;

    // Hacemos el constructor
    Gama(double precioBase) {
        this.precioBase = precioBase;
    }

    // Hacemos el get
    public double getPrecioBase() {
        return precioBase;
    }
}