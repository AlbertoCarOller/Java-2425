package Ejercicio9;

public class MontanaRocosa extends Montana {

    // Creamos los atributos
    private int altitud;
    private boolean cavernas;

    // Creamos el constructor
    public MontanaRocosa(String nombre, int altitud, boolean cavernas) throws ElementoException {
        super("Montaña Rocosa " + nombre, 50);
        setAltitud(altitud);
        this.cavernas = cavernas;
    }

    // Hacemos los get y set
    public int getAltitud() {
        return altitud;
    }

    private void setAltitud(int altitud) throws ElementoException {
        if (altitud < 10 || altitud > 600) {
            throw new ElementoException("Las montañas rocosas no pueden medir menos de 10 metros ni más de 600");
        }
        this.altitud = altitud;
    }

    public boolean isCavernas() {
        return cavernas;
    }

    private void setCavernas(boolean cavernas) {
        this.cavernas = cavernas;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + "\nAltitud: " + this.altitud + "\nCavernas: " + this.cavernas;
    }
}