package Ejercicio7;

public class RobotHibrido extends Robot {

    // Creamos la velocidad máxima
    protected static final int VELOCIDAD_MAX = 20;

    // Creamos el constructor
    public RobotHibrido(String nombre, int capacidadAdaptacion, int nivelEnergia) throws RobotException {
        super(nombre, capacidadAdaptacion, nivelEnergia);
    }

    // Implementamos el método del padre que sirve para sortear un obstáculo
    @Override
    public boolean superarObstaculo(Obstaculo obstaculo, int FINAL) throws RobotException {
        int capacidadARestar;
        int probabilidadInmune = (int) (Math.random() * 100) + 1;
        if (probabilidadInmune <= super.getCapacidadAdaptacion()) {
            if ((super.getNivelEnergia() - 1) < 1) {
                super.setNivelEnergia(1);

            } else {
                super.setNivelEnergia(super.getNivelEnergia() - 1);
            }
            super.setNivelEnergia(super.getNivelEnergia() - 1);
            acelerar();
            if ((super.getDistanciaRecorrida() + (super.getVelocidadDesplazamiento() * 2)) >= FINAL) {
                super.setDistanciaRecorrida(FIN);
                return true;

            } else {
                super.setDistanciaRecorrida(super.getDistanciaRecorrida() + (super.getVelocidadDesplazamiento() * 2));
                capacidadARestar = 5;
            }

        } else {
            if ((super.getNivelEnergia() - 2) < 1) {
                super.setNivelEnergia(1);

            } else {
                super.setNivelEnergia(super.getNivelEnergia() - 2);
            }
            frenar();
            if ((super.getDistanciaRecorrida() + super.getVelocidadDesplazamiento()) >= FINAL) {
                super.setDistanciaRecorrida(FINAL);
                return true;

            } else {
                super.setDistanciaRecorrida(super.getDistanciaRecorrida() + super.getVelocidadDesplazamiento());
                capacidadARestar = 10;
            }
        }
        if (1 > (super.getCapacidadAdaptacion() - capacidadARestar)) {
            super.setCapacidadAdaptacion(1);

        } else if ((super.getCapacidadAdaptacion() - capacidadARestar) > 1) {
            super.setCapacidadAdaptacion(super.getCapacidadAdaptacion() - capacidadARestar);
        }
        return false;
    }

    @Override
    public void acelerar() throws RobotException {
        if (super.getVelocidadDesplazamiento() == VELOCIDAD_MAX) {
            return;
        }
        if (super.getVelocidadDesplazamiento() > (VELOCIDAD_MAX - 5)) {
            super.setVelocidadDesplazamiento(VELOCIDAD_MAX);

        } else {
            super.setVelocidadDesplazamiento(super.getVelocidadDesplazamiento() + 5);
        }
    }

    @Override
    public void frenar() throws RobotException {
        if (super.getVelocidadDesplazamiento() == 1) {
            return;
        }
        if ((super.getVelocidadDesplazamiento() - 5) < 1) {
            super.setVelocidadDesplazamiento(1);

        } else {
            super.setVelocidadDesplazamiento(super.getVelocidadDesplazamiento() - 5);
        }
    }
}