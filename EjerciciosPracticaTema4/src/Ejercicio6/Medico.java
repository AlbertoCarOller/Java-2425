package Ejercicio6;

public class Medico extends Luchador implements Curable {

    // Creamos el constructor
    public Medico(String nombre, int velocidad, int ataque, int defensa, int salud) throws LuchadorException {
        super(nombre, velocidad, ataque, defensa, salud);
        setAtaque(ataque);
    }

    // Modificamos el set del ataque
    protected void setAtaque(int ataque) throws LuchadorException {
        if (ataque > 70) {
            throw new LuchadorException("De 0 a 70");
        }
        super.setAtaque(ataque);
    }

    // Hacemos un método para atacar u quitarle vida al contricante
    @Override
    protected void atacar(Luchador victima) throws LuchadorException {
        if (victima.getSalud() == 0 || super.getSalud() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (victima.esquivar()) {
            System.out.println(victima.getNombre() + " ha esquivado");
            return;
        }
        int ataque = (int) (Math.random() * super.getAtaque()) + 1;
        if (ataque < victima.getDefensa()) {
            System.out.println(super.getNombre() + " le ha quitado a " + victima.getNombre() + " " + (victima.getDefensa() - ataque) + " de escudo");
            victima.setDefensa(victima.getDefensa() - ataque);

        } else {
            if (victima.getDefensa() != 0) {
                victima.setDefensa(0);
            }
            ataque = ataque - getDefensa();
            sb.append(super.getNombre()).append(" le ha quitado a ").append(victima.getNombre()).append(" todo el escudo");
            if (victima.getSalud() > ataque) {
                victima.setSalud(victima.getSalud() - ataque);
                sb.append(" y le ha dejado con ").append(victima.getSalud());

            } else {
                victima.setSalud(0);
                sb.append(" y le ha quitado la vida, ").append(victima.getNombre()).append(" ha muerto");
            }
            System.out.println(sb);
        }
    }

    // Implementamos el método de la interfaz
    @Override
    public void curar() throws LuchadorException {
        if (super.getSalud() == 0) {
            return;
        }
        int probabilidad = (int) (Math.random() * 100) + 1;
        if (probabilidad <= 20) {
            if (super.getSalud() == 100) {
                System.out.println("No puedes curarte, ya estás al máximo");

            } else {
                if (super.getSalud() <= 80) {
                    super.setSalud(super.getSalud() + 20);

                } else {
                    super.setSalud(100);
                }
            }
        }
    }
}