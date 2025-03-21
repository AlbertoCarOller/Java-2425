package EmpresaTransporte;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

public class Camion {

    // Creamos los atributos
    private String marca;
    private String modelo;
    private int consumoCombustible;
    private static final int MAX_REVISIONES = 5;
    private Revision[] revisiones;
    private Deposito depositoActual;

    // Creamos el constructor
    public Camion(String marca, String modelo, int consumoCombustible) throws CamionException {
        this.marca = marca;
        this.modelo = modelo;
        setConsumoCombustible(consumoCombustible);
        this.revisiones = new Revision[MAX_REVISIONES];
        this.depositoActual = new Deposito();
    }

    // Generamos los get y los set
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

    public int getConsumoCombustible() {
        return consumoCombustible;
    }

    private void setConsumoCombustible(int consumoCombustible) throws CamionException {

        if (consumoCombustible < 0) {
            throw new CamionException("El consumo de combustible no puede ser negativo");
        }
        this.consumoCombustible = consumoCombustible;
    }

    public Revision[] getRevisiones() {
        return revisiones;
    }

    private void setRevisiones(Revision[] revisiones) {
        this.revisiones = revisiones;
    }

    public Deposito getDepositoActual() {
        return depositoActual;
    }

    public void setDepositoActual(Deposito depositoActual) {
        this.depositoActual = depositoActual;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Camion{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", consumoCombustible=" + consumoCombustible +
                ", revisiones=" + Arrays.toString(revisiones) +
                ", depositoActual=" + depositoActual +
                '}';
    }

    // Hacemos un método para anadir una revisión a las revisiones del camión
    public void anadirPeriodo(Revision revision) throws CamionException {

        boolean hayEspacio = false;

        for (int i = 0; i < revisiones.length; i++) {

            if (revisiones[i] == null) {
                revisiones[i] = revision;
                hayEspacio = true;
                break;
            }
        }

        if (!hayEspacio) {
            throw new CamionException("No se pueden registrar más revisiones");
        }
    }

    // Hacemos un método para que el camión pueda viajar
    public void realizarViaje(int kilometros) throws CamionException {

        int posicionRevision = buscarRevision();

        LocalDate fechaViaje = LocalDate.now();

        if (revisiones[posicionRevision] != null) {

            if (!revisiones[posicionRevision].isPasada()) {
                throw new CamionException("No puede viajar, no pasó la inspección");
            }

            // La fecha límite va a ser la fecha en la que se creó la revisión más los días que dura la validéz
            LocalDate fechaLimite = revisiones[posicionRevision].getFecha()
                    .plus(revisiones[posicionRevision].periodoValidez);

            if (fechaViaje.isAfter(fechaLimite)) {
                throw new CamionException("La fecha límite ha pasado");
            }

            if (depositoActual.getCapacidadActual() < (kilometros * this.consumoCombustible)) {
                throw new CamionException("No hay suficiente combustible para realizar el viaje");
            }

            this.depositoActual.setCapacidadActual(this.depositoActual.getCapacidadActual() - (kilometros * this.consumoCombustible));

            int cada100Kilometros = (kilometros * this.consumoCombustible) / 100;
            System.out.println("Cada 100 kilómetros se ha consumido " + cada100Kilometros);

            mostrarPeriodosMeses(fechaViaje, revisiones[posicionRevision].getFecha(), fechaLimite);
        }
    }

    // Hacemos un método para mostrar los meses transcurridos
    public void mostrarPeriodosMeses(LocalDate fechaViaje, LocalDate fechaRevision, LocalDate fechaLimite) {

        long periodo1EnMeses = Period.between(fechaRevision, fechaViaje).toTotalMonths();
        long periodo2EnMeses = Period.between(fechaRevision, fechaLimite).toTotalMonths();

        System.out.println("Periodo entre la fecha de revisión y la fecha del viaje: " + periodo1EnMeses);
        System.out.println("Periodo límite: " + periodo2EnMeses);
    }

    // Hacemos un método que va a encontrar la revisión más reciente
    private int buscarRevision() throws CamionException {

        LocalDate mayor = null;
        int temporal = -1;

        for (int i = 0; i < revisiones.length; i++) {

            if (revisiones[i] == null) {
                continue;
            }

            if (temporal == -1) {
                temporal = i;
                mayor = revisiones[i].getFecha();
            }

            if (mayor.isBefore(revisiones[i].getFecha())) {
                mayor = revisiones[i].getFecha();
                temporal = i;
            }
        }

        if (temporal == -1) {
            throw new CamionException("No se han encontrado revisiones");
        }
        return temporal;
    }

    // Hacemos un método para rellenar el depósito del camión
    public void rellenarDeposito() {
        depositoActual.setCapacidadActual(Deposito.MAX_CAPACIDAD);
    }
}