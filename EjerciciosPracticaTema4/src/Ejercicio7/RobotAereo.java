package Ejercicio7;

public class RobotAereo extends Robot {

    // Creamos la velocidad máxima
    protected static final int MAX_VELOCIDAD = 30;

    // Creamos el constructor
    public RobotAereo(String nombre, int capacidadAdaptacion, int nivelEnergia) throws RobotException {
        super(nombre, capacidadAdaptacion, nivelEnergia);
    }

    @Override
    public boolean superarObstaculo(Obstaculo obstaculo, int FINAL) throws RobotException {
        if (obstaculo == Obstaculo.TORMETA_DE_ARENA || obstaculo == Obstaculo.CAMPO_MAGNETICO) {
            if ((super.getNivelEnergia() - 1) < 1) {
                super.setNivelEnergia(1);

            } else {
                super.setNivelEnergia(super.getNivelEnergia() - 1);
            }
            if ((super.getDistanciaRecorrida() + super.getVelocidadDesplazamiento()) >= FINAL) {
                super.setDistanciaRecorrida(FINAL);
                return true;

            } else {
                super.setDistanciaRecorrida(super.getDistanciaRecorrida() + super.getVelocidadDesplazamiento());
            }

        } else {
            if ((super.getNivelEnergia() - 2) < 1) {
                super.setNivelEnergia(1);

            } else {
                super.setNivelEnergia(super.getNivelEnergia() - 2);
            }
            int probabilidadInmune = (int) (Math.random() * 100) + 1;
            if (probabilidadInmune <= super.getCapacidadAdaptacion()) {
                if ((super.getDistanciaRecorrida() + (super.getVelocidadDesplazamiento() * 2)) >= FINAL) {
                    super.setDistanciaRecorrida(FINAL);
                    return true;

                } else {
                    super.setDistanciaRecorrida(super.getDistanciaRecorrida() + (super.getVelocidadDesplazamiento() * 2));
                }

            } else {
                if ((super.getDistanciaRecorrida() + (super.getVelocidadDesplazamiento())) >= FINAL) {
                    super.setDistanciaRecorrida(FINAL);
                    return true;

                } else {
                    super.setDistanciaRecorrida(super.getDistanciaRecorrida() + super.getVelocidadDesplazamiento());
                }
            }
        }
        if (obstaculo == Obstaculo.TORMETA_DE_ARENA) {
            if ((super.getVelocidadDesplazamiento() - obstaculo.getObstaculo()) < 1) {
                super.setVelocidadDesplazamiento(1);

            } else {
                super.setVelocidadDesplazamiento(super.getVelocidadDesplazamiento() - obstaculo.getObstaculo());
            }
        } else if (obstaculo == Obstaculo.CAMPO_MAGNETICO) {
            if ((super.getVelocidadDesplazamiento() - obstaculo.getObstaculo()) < 1) {
                super.setVelocidadDesplazamiento(1);

            } else {
                super.setVelocidadDesplazamiento(super.getVelocidadDesplazamiento() - obstaculo.getObstaculo());
            }
        }
        return false;
    }

    @Override
    public void acelerar() throws RobotException {
        if (super.getVelocidadDesplazamiento() == MAX_VELOCIDAD) {
            return;
        }
        if (super.getVelocidadDesplazamiento() > (MAX_VELOCIDAD - 5)) {
            super.setVelocidadDesplazamiento(MAX_VELOCIDAD);

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