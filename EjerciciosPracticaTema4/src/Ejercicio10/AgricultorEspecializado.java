package Ejercicio10;

public class AgricultorEspecializado extends Agricultor {

    // Creamos los atributos
    private EspecialidadCultivo especialidadCultivo;

    // Creamos el constructor
    public AgricultorEspecializado(String nombre, EspecialidadCultivo especialidadCultivo) {
        super(nombre);
        this.especialidadCultivo = especialidadCultivo;
    }

    // Hacemos los get y set
    public EspecialidadCultivo getEspecialidadCultivo() {
        return especialidadCultivo;
    }

    private void setEspecialidadCultivo(EspecialidadCultivo especialidadCultivo) {
        this.especialidadCultivo = especialidadCultivo;
    }

    @Override
    public void plantar(Cultivo cultivo, Granja granja) throws CultivoException {
        if (granja.getAguaDisponible() < 150 || granja.getCantidadFertilizante() < 150) {
            throw new CultivoException("No tienes suficientes recursos para plantar");
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
            if (cultivo instanceof Fruta) {
                cultivo.setCantidadProducida(70);
            }
            boolean mismoTipo = false;
            if (this.especialidadCultivo == EspecialidadCultivo.HORTALIZA && cultivo instanceof Hortaliza) {
                mismoTipo = true;
                cultivo.setCantidadProducida(100);
            }
            if (this.especialidadCultivo == EspecialidadCultivo.FRUTA && cultivo instanceof Fruta) {
                mismoTipo = true;
                cultivo.setCantidadProducida(100);
            }
            if (this.especialidadCultivo == EspecialidadCultivo.CEREAL && cultivo instanceof Cereal) {
                mismoTipo = true;
                cultivo.setCantidadProducida(100);
            }
            if (mismoTipo) {
                granja.setAguaDisponible(granja.getAguaDisponible() - 50);
                granja.setCantidadFertilizante(granja.getCantidadFertilizante() - 50);
            } else {
                granja.setAguaDisponible(granja.getAguaDisponible() - 150);
                granja.setCantidadFertilizante(granja.getCantidadFertilizante() - 150);
            }
            granja.getCultivos()[hayEspacio] = cultivo;

        } else {
            throw new CultivoException("No se puede plantar el cultivo");
        }
    }

    // Hacemos un toString modificado para añadir el atributo concreto de esta clase
    @Override
    public String toString() {
        return super.toString() + ", Especialidad cultivo: " + this.especialidadCultivo;
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
                if (granja.getCultivos()[i] instanceof Hortaliza && especialidadCultivo == EspecialidadCultivo.HORTALIZA) {
                    granja.getCultivos()[i] = null;
                    break;

                } else if (granja.getCultivos()[i] instanceof Fruta && especialidadCultivo == EspecialidadCultivo.FRUTA) {
                    granja.getCultivos()[i] = null;
                    break;

                } else if (granja.getCultivos()[i] instanceof Cereal && especialidadCultivo == EspecialidadCultivo.CEREAL) {
                    granja.getCultivos()[i] = null;
                    break;
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
            if (granja.getCultivos()[i] == null) {
                continue;
            }
            if (nombreCultivo.equalsIgnoreCase(granja.getCultivos()[i].getNombre())) {
                encontrado = true;
                if (granja.getCultivos()[i].getLitrosAguaNecesaria() == 0) {
                    return;
                }
                if (especialidadCultivo == EspecialidadCultivo.CEREAL && granja.getCultivos()[i] instanceof Cereal ||
                        especialidadCultivo == EspecialidadCultivo.HORTALIZA && granja.getCultivos()[i] instanceof Hortaliza ||
                        especialidadCultivo == EspecialidadCultivo.FRUTA && granja.getCultivos()[i] instanceof Fruta) {
                    if (granja.getAguaDisponible() >= 15) {
                        if ((granja.getCultivos()[i].getLitrosAguaNecesaria() - 15) < 0) {
                            granja.setAguaDisponible(granja.getAguaDisponible() - granja.getCultivos()[i].getLitrosAguaNecesaria());
                            granja.getCultivos()[i].setLitrosAguaNecesaria(0);

                        } else {
                            granja.setAguaDisponible(granja.getAguaDisponible() - 15);
                            granja.getCultivos()[i].setLitrosAguaNecesaria(granja.getCultivos()[i].getLitrosAguaNecesaria() - 15);
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
                } else {
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
                }
                break;
            }
        }
        if (!encontrado) {
            throw new CultivoException("No se ha encontrado el cultivo");
        }
    }
}