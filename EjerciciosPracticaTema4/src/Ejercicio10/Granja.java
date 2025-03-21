package Ejercicio10;

import java.util.Arrays;

public class Granja {

    // Creamos los atributos
    private Cultivo[] cultivos;
    private static final int MAX_CULTIVOS = 6;
    private int aguaDisponible;
    private static final int MAX_AGUA = 1500;
    private int cantidadFertilizante;
    private static final int MAX_FERTILIZANTE = 1500;
    private Tiempo tiempoActual;
    private Tiempo[] tiempos;
    private Agricultor[] agricultores;
    private static final int MAX_AGRICULTORES = 3;

    // Creamos el constructor
    public Granja() {
        this.cultivos = new Cultivo[MAX_CULTIVOS];
        this.aguaDisponible = MAX_AGUA;
        this.cantidadFertilizante = MAX_FERTILIZANTE;
        this.tiempoActual = Tiempo.TIEMPO_FAFORABLE;
        this.tiempos = new Tiempo[]{Tiempo.CALOR, Tiempo.TIEMPO_FAFORABLE, Tiempo.FRIO};
        this.agricultores = new Agricultor[MAX_AGRICULTORES];
    }

    // Creamos los get y set
    public Cultivo[] getCultivos() {
        return cultivos;
    }

    private void setCultivos(Cultivo[] cultivos) {
        this.cultivos = cultivos;
    }

    public int getAguaDisponible() {
        return aguaDisponible;
    }

    protected void setAguaDisponible(int aguaDisponible) {
        this.aguaDisponible = aguaDisponible;
    }

    public int getCantidadFertilizante() {
        return cantidadFertilizante;
    }

    protected void setCantidadFertilizante(int cantidadFertilizante) {
        this.cantidadFertilizante = cantidadFertilizante;
    }

    public Tiempo getTiempoActual() {
        return tiempoActual;
    }

    protected void setTiempoActual(Tiempo tiempoActual) {
        this.tiempoActual = tiempoActual;
    }

    public Tiempo[] getTiempos() {
        return tiempos;
    }

    private void setTiempos(Tiempo[] tiempos) {
        this.tiempos = tiempos;
    }

    public Agricultor[] getAgricultores() {
        return agricultores;
    }

    private void setAgricultores(Agricultor[] agricultores) {
        this.agricultores = agricultores;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Cultivos: %s, Agua disponible: %d, Cantidad fertilizante: %d, Tiempo actual: %s, Agricultores: %s",
                Arrays.toString(this.cultivos), this.aguaDisponible, this.cantidadFertilizante, this.tiempoActual,
                Arrays.toString(this.agricultores));
    }

    // Hacemos un método que va a devolver un tiempo aleatoriamente
    private void generarTiempoAleatorio() {
        int probabilidadTiempo = (int) (Math.random() * 3);
        this.tiempoActual = this.tiempos[probabilidadTiempo];
    }

    // Hacemos un método para simular una plaga
    private void ataquePlaga() throws CultivoException {
        for (int i = 0; i < cultivos.length; i++) {
            boolean evitarDano = false;
            if (cultivos[i] != null) {
                for (int j = 0; j < agricultores.length; j++) {
                    if (agricultores[j] instanceof AgricultorEspecializado agricultorEspecializado) {
                        if (agricultorEspecializado.getEspecialidadCultivo() == EspecialidadCultivo.HORTALIZA &&
                                cultivos[i] instanceof Hortaliza) {
                            evitarDano = true;
                        }
                        if (agricultorEspecializado.getEspecialidadCultivo() == EspecialidadCultivo.FRUTA &&
                                cultivos[i] instanceof Fruta) {
                            evitarDano = true;
                        }
                        if (agricultorEspecializado.getEspecialidadCultivo() == EspecialidadCultivo.CEREAL &&
                                cultivos[i] instanceof Cereal) {
                            evitarDano = true;
                        }
                    }
                }
                if (evitarDano) {
                    continue;
                }
                if ((cultivos[i].getResistenciaPlagas() - 10) <= 0) {
                    cultivos[i] = null;

                } else {
                    cultivos[i].setResistenciaPlagas(cultivos[i].getResistenciaPlagas() - 10);
                }
            }
        }
    }

    // Hacemos un método que va a restar tiempo de crecimiento a los cultivos
    private void restarTiempo() throws CultivoException {
        int tiempoARestar;
        for (int i = 0; i < cultivos.length; i++) {
            if (cultivos[i] != null) {
                if (cultivos[i].getTiempoCrecimiento() == 0) {
                    continue;
                }
                if (tiempoActual == Tiempo.TIEMPO_FAFORABLE) {
                    tiempoARestar = 15;

                } else {
                    tiempoARestar = 7;
                }
                if ((cultivos[i].getTiempoCrecimiento() - tiempoARestar) < 0) {
                    cultivos[i].setTiempoCrecimiento(0);

                } else {
                    cultivos[i].setTiempoCrecimiento(cultivos[i].getTiempoCrecimiento() - tiempoARestar);
                }
            }
        }
    }

    // Hacemos un método para sumar agua
    private void sumarAgua() throws CultivoException {
        int aguaASumar;
        for (int i = 0; i < cultivos.length; i++) {
            if (cultivos[i] != null) {
                if (cultivos[i].getLitrosAguaNecesaria() == 250) {
                    System.out.println(cultivos[i].getNombre() + " ha muerto, mucho tiempo sin regarse");
                }
                if (tiempoActual == Tiempo.TIEMPO_FAFORABLE) {
                    aguaASumar = 5;

                } else {
                    aguaASumar = 10;
                }
                if ((cultivos[i].getLitrosAguaNecesaria() + aguaASumar) > 250) {
                    cultivos[i].setLitrosAguaNecesaria(250);

                } else {
                    cultivos[i].setLitrosAguaNecesaria(cultivos[i].getLitrosAguaNecesaria() + aguaASumar);
                }
            }
        }
    }

    // Hacemos un método para reponer el agua y fertilizante de la granja
    public void reponerRecursos() throws CultivoException {
        if (aguaDisponible == MAX_AGUA && cantidadFertilizante == MAX_FERTILIZANTE) {
            throw new CultivoException("Están todos los recursos al máximo");
        }
        this.aguaDisponible = MAX_AGUA;
        this.cantidadFertilizante = MAX_FERTILIZANTE;
    }

    // TODO: Hacer un método que simule un ciclo, hacer el main
}