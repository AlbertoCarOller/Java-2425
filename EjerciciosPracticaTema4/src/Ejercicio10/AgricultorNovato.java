package Ejercicio10;

public class AgricultorNovato extends Agricultor {

    // Creamos el constructor
    public AgricultorNovato(String nombre) {
        super(nombre);
    }

    @Override
    public void plantar(Cultivo cultivo, Granja granja) throws CultivoException {
        if (granja.getAguaDisponible() < 150 || granja.getCantidadFertilizante() < 150) {
            throw new CultivoException("No tienes suficientes recursos para plantar");
        }
        int probabilidadFallo = (int) (Math.random() * 100) + 1;
        if (probabilidadFallo <= 70) {
            throw new CultivoException(this.getNombre() + " ha fallado al plantar");
        }
        int hayEspacio = -1;
        boolean esIgual = false;
        for (int i = 0; i < granja.getCultivos().length && !esIgual; i++) {
            if (cultivo.equals(granja.getCultivos()[i])) {
                esIgual = true;
            }
            if (granja.getCultivos()[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            granja.setAguaDisponible(granja.getAguaDisponible() - 150);
            granja.setCantidadFertilizante(granja.getCantidadFertilizante() - 150);
            if (cultivo instanceof Fruta) {
                cultivo.setCantidadProducida(70);
            }
            granja.getCultivos()[hayEspacio] = cultivo;

        } else {
            throw new CultivoException("No se puede plantar el cultivo");
        }
    }

    @Override
    public void cosechar(String nombreCultivo, Granja granja) throws CultivoException {
        boolean encontrado = false;
        for (int i = 0; i < granja.getCultivos().length && !encontrado; i++) {
            if (granja.getCultivos()[i] == null) {
                continue;
            }
            if (nombreCultivo.equalsIgnoreCase(granja.getCultivos()[i].getNombre())) {
                encontrado = true;
                if (granja.getCultivos()[i].getLitrosAguaNecesaria() != 0) {
                    throw new CultivoException("No puedes cosechar " + nombreCultivo + " ya que aún necesita más agua");
                }
                if (granja.getCultivos()[i].getTiempoCrecimiento() != 0) {
                    throw new CultivoException("Todavía necesita más tiempo para que se pueda cosechar");
                }
                int probabilidadFallar = (int) (Math.random() * 100) + 1;
                if (probabilidadFallar <= 50) {
                    System.out.println(super.getNombre() + " ha fallado");
                    if ((granja.getCultivos()[i].getResistenciaPlagas() - 5) < 0) {
                        granja.getCultivos()[i] = null;
                        System.out.println(super.getNombre() + " ha destrozado el cultivo " + nombreCultivo);

                    } else {
                        granja.getCultivos()[i].setResistenciaPlagas(granja.getCultivos()[i].getResistenciaPlagas() - 5);
                    }
                } else {
                    int probabilidadNoCosechar = (int) (Math.random() * super.getEquipo().length) + 1;
                    if (super.numHerramientas() >= probabilidadNoCosechar) {
                        granja.getCultivos()[i] = null;

                    } else {
                        throw new CultivoException(super.getNombre() + " ha fallado");
                    }
                }
            }
        }
        if (!encontrado) {
            throw new CultivoException("No se ha encontrado el cultivo");
        }
    }

    @Override
    public void regar(String nombreCultivo, Granja granja) throws CultivoException {
        if (granja.getAguaDisponible() == 0) {
            throw new CultivoException("No queda agua en la granja");
        }
        boolean encontrado = false;
        for (int i = 0; i < granja.getCultivos().length; i++) {
            if (nombreCultivo.equalsIgnoreCase(granja.getCultivos()[i].getNombre())) {
                encontrado = true;
                if (granja.getCultivos()[i].getLitrosAguaNecesaria() == 0) {
                    return;
                }
                int probabilidadFallo = (int) (Math.random() * 100) + 1;
                if (probabilidadFallo >= 50) {
                    throw new CultivoException("El agricultor ha fallado");
                }
                if (granja.getAguaDisponible() >= 8) {
                    if ((granja.getCultivos()[i].getLitrosAguaNecesaria() - 8) < 0) {
                        granja.setAguaDisponible(granja.getAguaDisponible() - granja.getCultivos()[i].getLitrosAguaNecesaria());
                        granja.getCultivos()[i].setLitrosAguaNecesaria(0);

                    } else {
                        granja.setAguaDisponible(granja.getAguaDisponible() - 8);
                        granja.getCultivos()[i].setLitrosAguaNecesaria(granja.getCultivos()[i].getLitrosAguaNecesaria() - 8);
                    }
                } else {
                    if (granja.getAguaDisponible() < granja.getCultivos()[i].getLitrosAguaNecesaria()) {
                        granja.getCultivos()[i].setLitrosAguaNecesaria(granja.getCultivos()[i].getLitrosAguaNecesaria() - granja.getAguaDisponible());
                        granja.setAguaDisponible(0);

                    } else {
                        granja.setAguaDisponible(granja.getAguaDisponible() - granja.getCultivos()[i].getLitrosAguaNecesaria());
                        granja.getCultivos()[i].setLitrosAguaNecesaria(0);
                    }
                }
                break;
            }
        }
        if (!encontrado) {
            throw new CultivoException("No se ha encontrado el cultivo");
        }
    }
}