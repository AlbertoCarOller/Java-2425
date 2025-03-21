package Avion;

import java.time.LocalDate;
import java.util.Arrays;

public class Avion {

    // Hacemos los atributos
    private String marca;
    private String modelo;
    private int consumo;
    private Revision[] revisiones;
    private static final int MAX_REVISION = 4;
    private Deposito deposito;

    // Hacemos el constructor
    public Avion(String marca, String modelo, int consumo) throws AvionExcepcion {
        this.marca = marca;
        this.modelo = modelo;
        setConsumo(consumo);
        this.revisiones = new Revision[MAX_REVISION];
        this.deposito = new Deposito();
    }

    // Hacemos los get y set
    public String getMarca() {
        return marca;
    }

    private void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    private void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getConsumo() {
        return consumo;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Revision[] getRevisiones() {
        return revisiones;
    }

    public void setRevisiones(Revision[] revisiones) {
        this.revisiones = revisiones;
    }

    private void setConsumo(int consumo) throws AvionExcepcion {

        if (consumo < 0) {
            throw new AvionExcepcion("El consumo del avión no puede ser negativo");
        }
        this.consumo = consumo;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Avion{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", consumo=" + consumo +
                ", revisiones=" + Arrays.toString(revisiones) +
                ", deposito=" + deposito +
                '}';
    }

    // Hacemos un método para añadir revisiones
    public void hacerRevision(Revision revision) throws AvionExcepcion {

        boolean hayEspacio = false;

        for (int i = 0; i < revisiones.length; i++) {

            if (revisiones[i] == null) {
                hayEspacio = true;
                revisiones[i] = revision;
                break;
            }
        }

        if (!hayEspacio) {
            throw new AvionExcepcion("No se pueden realizar más revisiones");
        }
    }

    // Hacemos un método para comprobar la última revisión
    public void comprobarUltimaRevision(int distanciaADestino) throws AvionExcepcion {

        LocalDate fechaActual = LocalDate.now();

        for (int i = 0; i < revisiones.length; i++) {

            // Cuando encuentre la última revisión entra
            if (revisiones[revisiones.length - 1 - i] != null) {

                if (revisiones[revisiones.length - 1 - i].getEstado().equalsIgnoreCase("incorrecto")) {
                    throw new AvionExcepcion("El avión no pudo pasar la última revisión");
                }

                // Le sumamos el periodo a la fecha y creamos una fecha límite
                LocalDate fechaLimite = revisiones[revisiones.length - 1 - i].getFecha()
                        .plus(revisiones[revisiones.length - 1 - i].getPeriodoValidez());

                // Si el periodo de tiempo válido es menor que el periodo entre la fecha de la revisión y la fecha actual entra
                if (fechaActual.isAfter(fechaLimite)) {
                    throw new AvionExcepcion("El periodo límite pasó");
                }

                if (deposito.getCapacidadActual() < distanciaADestino * consumo) {
                    throw new AvionExcepcion("No hay suficiente combustible");
                }

                // Le quitamos al deposito del avión lo consumido
                deposito.setCapacidadActual(deposito.getCapacidadActual() - (distanciaADestino * consumo));
                break;
            }
        }
    }

    // Hacemos un método para rellenar el depósito
    public void rellenarDiposito() {
        this.deposito.setCapacidadActual(Deposito.MAX_CAPACIDAD);
    }
}