package Boletin1.Personaje;

import java.util.Arrays;

public class Mago extends Personaje {

    // Creamos un atributo que almacena los nombres de los hechizos
    private String[] hechizos;
    private static final int MAX_HECHIZOS = 4;

    // Hacemos el constructor
    public Mago(String nombre, Raza raza, int fuerza, int inteligencia, int ptsMax, int ptsActual) throws PersonaException {
        super(nombre, raza, fuerza, inteligencia, ptsMax, ptsActual);
        setInteligencia(inteligencia);
        setFuerza(fuerza);
        this.hechizos = new String[MAX_HECHIZOS];
    }

    // Hacemos los set modificados
    protected void setInteligencia(int inteligencia) throws PersonaException {
        if (inteligencia < 17) {
            throw new PersonaException("Los magos no pueden tener menos de 17 de inteligencia");
        }
        super.setInteligencia(inteligencia);
    }

    protected void setFuerza(int fuerza) throws PersonaException {
        if (fuerza > 15) {
            throw new PersonaException("Los magos no pueden tener más de 15 de fuerza");
        }
        super.setFuerza(fuerza);
    }

    // Creamos un método por el cual vamos a introducir el nombre de un hechizo a un array
    protected void aprendeHechizo(String hechizo) throws PersonaException {

        boolean sonIguales = false;
        int hayEspacio = -1;

        for (int i = 0; i < hechizos.length && !sonIguales; i++) {

            if (hechizo.equals(hechizos[i])) {
                sonIguales = true;
            }

            if (hechizos[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (!sonIguales && hayEspacio != -1) {
            hechizos[hayEspacio] = hechizo;

        } else {
            throw new PersonaException("El mago no puede aprender el hechizo");
        }
    }

    // Creamos un método para que el mago pueda lanzar un hechizo a otro personaje
    protected void lanzarHechizo(Personaje victima, String hechizoALanzar) throws PersonaException {

        boolean hechizoEncontrado = false;

        if (victima.getNombre().equals(this.getNombre())) {
            throw new PersonaException("No puedes lanzarte el hechizo a ti mismo");
        }

        for (int i = 0; i < hechizos.length; i++) {

            if (hechizoALanzar.equals(hechizos[i])) {
                hechizoEncontrado = true;
                hechizos[i] = null;

                /* Si la vida de la víctima es mayor que 10, le quitamos la vida, ya que sino va a a quedar en negativo
                 los puntos de vida y por lo tanto nos van saltar las excepciones del set */
                if (victima.getPtsActual() > 10) {
                    victima.setPtsActual(victima.getPtsActual() - 10);

                } else {
                    victima.setPtsActual(0);
                    System.out.println("La víctima a muerto");
                }
            }
        }

        if (!hechizoEncontrado) {
            throw new PersonaException("No conoces el hechizo");
        }
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Mago{" +
                "nombre=" + this.getNombre() + ", " +
                "raza=" + this.getRaza() + ", " +
                "fuerza=" + this.getFuerza() + ", " +
                "inteligencia=" + this.getInteligencia() + ", " +
                "ptsMax=" + this.getPtsMax() + ", " +
                "ptsActual=" + this.getPtsActual() + ", " +
                "hechizos=" + Arrays.toString(hechizos) +
                '}';
    }
}