package Boletin1.Personaje;

public class Clerigo extends Personaje {

    // Creamos atributos
    private String nombreDios;

    // Creamos el constructor
    public Clerigo(String nombre, Raza raza, int fuerza, int inteligencia, int ptsMax, int ptsActual, String nombreDios) throws PersonaException {
        super(nombre, raza, fuerza, inteligencia, ptsMax, ptsActual);
        setFuerza(fuerza);
        setInteligencia(inteligencia);
        this.nombreDios = nombreDios;
    }

    // Modificamos los set para adaptarlo al clerigo
    protected void setFuerza(int fuerza) throws PersonaException {
        if (fuerza < 18) {
            throw new PersonaException("La cantidad de puntos de fuerza no es válido");
        }
        super.setFuerza(fuerza);
    }

    protected void setInteligencia(int inteligencia) throws PersonaException {
        if (inteligencia < 12 || inteligencia > 16) {
            throw new PersonaException("La cantidad de puntos de inteligencia no es válido");
        }
        super.setInteligencia(inteligencia);
    }

    // Hacemos un método que cure a un personaje
    protected void curar(Personaje personaje) throws PersonaException {

        if (personaje.getNombre().equals(this.getNombre())) {
            throw new PersonaException("No te puedes curar a ti mismo");
        }
        if (personaje.getPtsActual() < personaje.getPtsMax()) {
            personaje.setPtsActual(personaje.getPtsActual() + 10);

        } else {
            personaje.setPtsActual(personaje.getPtsMax());
            throw new PersonaException("No te puedes curar, estás al máximo");
        }
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Clerigo{" +
                "nombre=" + this.getNombre() + ", " +
                "raza=" + this.getRaza() + ", " +
                "fuerza=" + this.getFuerza() + ", " +
                "inteligencia=" + this.getInteligencia() + ", " +
                "ptsMax=" + this.getPtsMax() + ", " +
                "ptsActual=" + this.getPtsActual() + ", " +
                "nombreDios='" + nombreDios + '\'' +
                '}';
    }
}