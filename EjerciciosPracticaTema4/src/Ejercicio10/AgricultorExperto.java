package Ejercicio10;

public class AgricultorExperto extends Agricultor {

    // Creamos el constructor
    public AgricultorExperto(String nombre) {
        super(nombre);
    }

    @Override
    public void plantar(Cultivo cultivo, Granja granja) throws CultivoException {
        if (granja.getAguaDisponible() < 100 || granja.getCantidadFertilizante() < 100) {
            throw new CultivoException("No tienes suficientes recursos para plantar");
        }
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < granja.getCultivos().length && !esIgual; i++) {
            if (cultivo.equals(granja.getCultivos()[i])) {
                esIgual = true;
            }
            if (granja.getCultivos()[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            granja.setAguaDisponible(granja.getAguaDisponible() - 100);
            granja.setCantidadFertilizante(granja.getCantidadFertilizante() - 100);
            if (cultivo instanceof Fruta) {
                cultivo.setCantidadProducida(70);
            }
            mejorarCosecha(cultivo);
            granja.getCultivos()[hayEspacio] = cultivo;

        } else {
            throw new CultivoException("No se ha podido plantar");
        }
    }

    @Override
    public void cosechar(String nombreCultivo, Granja granja) throws CultivoException {
        boolean encontrado = false;
        for (int i = 0; i < granja.getCultivos().length; i++) {
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
                int probabilidadNoCosechar = (int) (Math.random() * super.getEquipo().length) + 1;
                if (super.numHerramientas() >= probabilidadNoCosechar) {
                    granja.getCultivos()[i] = null;

                } else {
                    throw new CultivoException(super.getNombre() + " ha fallado");
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

    // Hacemos un método que mejora la cosecha
    private void mejorarCosecha(Cultivo cultivo) throws CultivoException {
        if (cultivo.getCantidadProducida() == 100) {
            return;
        }
        if ((cultivo.getCantidadProducida() + 10) > 100) {
            cultivo.setCantidadProducida(100);

        } else {
            cultivo.setCantidadProducida(cultivo.getCantidadProducida() + 10);
        }
    }
}