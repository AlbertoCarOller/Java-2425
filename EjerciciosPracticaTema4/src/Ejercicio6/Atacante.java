package Ejercicio6;

public class Atacante extends Luchador implements AtaqueCritico {

    // Creamos el constructor
    public Atacante(String nombre, int velocidad, int ataque, int defensa, int salud) throws LuchadorException {
        super(nombre, velocidad, ataque, defensa, salud);
    }

    // Modificamos el set de la defensa
    protected void setDefensa(int defensa) throws LuchadorException {
        if (defensa < 0 || defensa > 70) {
            throw new LuchadorException("De 0 a 70");
        }
        super.setDefensa(defensa);
    }

    @Override
    protected void atacar(Luchador victima) throws LuchadorException {
        if (victima.getSalud() == 0 || super.getSalud() == 0) {
            return;
        }
        if (victima.esquivar()) {
            System.out.println(victima.getNombre() + " ha esquivado");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int ataque = (int) (Math.random() * super.getAtaque()) + 1;
        if (ataqueCritico()) {
            ataque = ataque + 20;
        }
        if (ataque < victima.getDefensa()) {
            System.out.println(super.getNombre() + " le ha quitado a " + victima.getNombre() + " " + (victima.getDefensa() - ataque) + " de escudo");
            victima.setDefensa(victima.getDefensa() - ataque);

        } else {
            if (victima.getDefensa() != 0) {
                victima.setDefensa(0);
            }
            sb.append(super.getNombre()).append(" le ha quitado a ").append(victima.getNombre()).append(" todo el escudo");
            ataque = ataque - victima.getDefensa();
            if (victima.getSalud() > ataque) {
                victima.setSalud(victima.getSalud() - ataque);
                sb.append(" y le ha dejado con ").append(victima.getSalud());


            } else {
                victima.setSalud(0);
                sb.append(" y le ha quitado la vida, ").append(victima.getNombre()).append(" ha muerto");
            }
        }
        System.out.println(sb);
    }

    @Override
    public boolean ataqueCritico() {
        int probabilidad = (int) (Math.random() * 10) + 1;
        if (probabilidad <= 2) {
            return true;
        }
        return false;
    }
}