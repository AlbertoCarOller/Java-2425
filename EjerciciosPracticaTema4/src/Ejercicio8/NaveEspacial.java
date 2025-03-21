package Ejercicio8;

import java.util.Arrays;
import java.util.Objects;

public abstract class NaveEspacial {

    // Creamos los atributos
    private String nombre;
    private int salud;
    private int horasAutonomiaEnergetica;
    private Combustible combustible;
    private final int CAPACIDAD_MAX;
    private boolean terraformado;
    private boolean defensaRadiacion;
    private ViajeInterplanetario[] viajesInterplanetarios;
    private static final int MAX_VIAJES = 5;
    private Tripulante[] tripulantes;
    private static final int MAX_TRIPULANTES = 4;

    // Creamos el constructor
    public NaveEspacial(String nombre, int horasAutonomiaEnergetica, Combustible combustible, int CAPACIDAD_MAX, boolean terraformado, boolean defensaRadiacion) throws NaveEspacialException {
        this.nombre = nombre;
        this.salud = 350;
        this.horasAutonomiaEnergetica = horasAutonomiaEnergetica;
        this.combustible = combustible;
        this.CAPACIDAD_MAX = CAPACIDAD_MAX;
        this.terraformado = terraformado;
        this.defensaRadiacion = defensaRadiacion;
        this.viajesInterplanetarios = new ViajeInterplanetario[MAX_VIAJES];
        this.tripulantes = new Tripulante[MAX_TRIPULANTES];
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Combustible getCombustible() {
        return combustible;
    }

    protected void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }

    public int getCAPACIDAD_MAX() {
        return CAPACIDAD_MAX;
    }

    public boolean isTerraformado() {
        return terraformado;
    }

    private void setTerraformado(boolean terraformado) {
        this.terraformado = terraformado;
    }

    public boolean isDefensaRadiacion() {
        return defensaRadiacion;
    }

    private void setDefensaRadiacion(boolean defensaRadiacion) {
        this.defensaRadiacion = defensaRadiacion;
    }

    public ViajeInterplanetario[] getViajesInterplanetarios() {
        return viajesInterplanetarios;
    }

    protected void setViajesInterplanetarios(ViajeInterplanetario[] viajesInterplanetarios) {
        this.viajesInterplanetarios = viajesInterplanetarios;
    }

    public Tripulante[] getTripulantes() {
        return tripulantes;
    }

    protected void setTripulantes(Tripulante[] tripulantes) {
        this.tripulantes = tripulantes;
    }

    public int getSalud() {
        return salud;
    }

    protected void setSalud(int salud) {
        this.salud = salud;
    }

    public int getHorasAutonomiaEnergetica() {
        return horasAutonomiaEnergetica;
    }

    protected void setHorasAutonomiaEnergetica(int horasAutonomiaEnergetica) throws NaveEspacialException {
        this.horasAutonomiaEnergetica = horasAutonomiaEnergetica;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NaveEspacial that = (NaveEspacial) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("La nave %s tiene %d de salud, unas %d horas de autonomía, utiliza el combustible %s" +
                        ", tiene una capacidad máxima de %d, puede terraformar %b, tiene protección contra radiación %b," +
                        " sus viajes son %s y su tripulación está formada por %s", this.nombre, this.salud, this.horasAutonomiaEnergetica,
                this.combustible, this.CAPACIDAD_MAX, this.terraformado, this.defensaRadiacion, Arrays.toString(this.viajesInterplanetarios),
                Arrays.toString(this.tripulantes));
    }

    // Hacemos un método para realizar un viaje
    public abstract void realizarViaje(ViajeInterplanetario viajeInterplanetario) throws NaveEspacialException;

    // Hacemos un método para añadir un tripulante a la nave
    public void anadirTripulante(Tripulante tripulante) throws NaveEspacialException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < tripulantes.length && !esIgual; i++) {
            if (tripulante.equals(tripulantes[i])) {
                esIgual = true;
            }
            if (tripulantes[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            tripulantes[hayEspacio] = tripulante;

        } else {
            throw new NaveEspacialException("No se pueden añadir tripulantes");
        }
    }

    // Hacemos un método para eliminar un tripulante
    public void eliminarTripulante(String nombreTripulante) throws NaveEspacialException {
        boolean encontrado = false;
        for (int i = 0; i < tripulantes.length; i++) {
            if (tripulantes[i] == null) {
                continue;
            }
            if (nombreTripulante.equalsIgnoreCase(tripulantes[i].getNombre())) {
                tripulantes[i] = null;
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new NaveEspacialException("No se ha econtrado el tripulante");
        }
    }

    // Hacemos un método para contar cuantos tripulantes hay en la nave
    public int contarTripulantes() {
        int contador = 0;
        for (Tripulante tripulante : tripulantes) {
            if (tripulante != null) {
                contador++;
            }
        }
        return contador;
    }

    // Hacemos un método para comprobar si hay piloto
    public boolean hayPiloto() {
        for (Tripulante tripulante : tripulantes) {
            if (tripulante instanceof Piloto) {
                return true;
            }
        }
        return false;
    }

    // Hacemos un método para quitarle vida a los tripulantes
    protected void quitarVidaTripulantes() {
        for (int i = 0; i < this.tripulantes.length; i++) {
            if (tripulantes[i] == null) {
                continue;
            }
            if ((tripulantes[i].getSalud() - 5) < 1) {
                tripulantes[i].setSalud(1);

            } else {
                tripulantes[i].setSalud(tripulantes[i].getSalud() - 5);
            }
        }
    }
}