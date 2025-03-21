package Ejercicio9;

public class Pino extends Arbol {

    // Creamos los atributos
    private int edad;
    private Pina tipoPina;

    // Creamos el constructor
    public Pino(String nombre, int edad, Pina tipoPina) throws ElementoException {
        super("Pino " + nombre, 40);
        setEdad(edad);
        this.tipoPina = tipoPina;
    }

    // Hacemos los get y set
    public int getEdad() {
        return edad;
    }

    private void setEdad(int edad) throws ElementoException {
        if (edad < 1 || edad > 300) {
            throw new ElementoException("La edad del pino no puede ser menor a 1 ni mayor a 300 años");
        }
        this.edad = edad;
    }

    public Pina getTipoPina() {
        return tipoPina;
    }

    private void setTipoPina(Pina tipoPina) {
        this.tipoPina = tipoPina;
    }

    // Hacemos el toString
    @Override
    public String toString() {
        return super.toString() + "\nEdad: " + this.edad + "\nTipo Piña: " + this.tipoPina;
    }
}