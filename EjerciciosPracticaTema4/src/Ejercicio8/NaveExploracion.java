package Ejercicio8;

import java.util.Arrays;

public class NaveExploracion extends NaveEspacial implements Auxiliable {

    // Creamos los atributos
    private Sensor[] sensores;
    private static final int MAX_SENSORES = 2;

    // Creamos el constructor
    public NaveExploracion(String nombre, int horasAutonomiaEnergetica, Combustible combustible, int CAPACIDAD_MAX,
                           boolean terraformado, boolean defensaRadiacion) throws NaveEspacialException {
        super(nombre, horasAutonomiaEnergetica, combustible, CAPACIDAD_MAX, terraformado, defensaRadiacion);
        setHorasAutonomiaEnergetica(horasAutonomiaEnergetica);
        if (super.getCAPACIDAD_MAX() < 5 || super.getCAPACIDAD_MAX() > 80) {
            throw new NaveEspacialException("La capacidad de pasajeros no puede ser menos de 5 ni mayor a 80");
        }
        this.sensores = new Sensor[MAX_SENSORES];
        setHorasAutonomiaEnergetica(horasAutonomiaEnergetica);
    }

    // Hacemos los get y set
    public Sensor[] getSensores() {
        return sensores;
    }

    private void setSensores(Sensor[] sensores) {
        this.sensores = sensores;
    }

    public void setHorasAutonomiaEnergetica(int horasAutonomiaEnergetica) throws NaveEspacialException {
        if (horasAutonomiaEnergetica < 48 || horasAutonomiaEnergetica > 96) {
            throw new NaveEspacialException("Las naves de exploración no pueden tener menos de 48 horas de autonomía ni más de 96");
        }
        super.setHorasAutonomiaEnergetica(horasAutonomiaEnergetica);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + ",Sensores=" + Arrays.toString(sensores);
    }

    // Hacemos un método para comprobar que la nave tenga sensores
    public boolean haySensores() {
        for (Sensor sensor : sensores) {
            if (sensor != null) {
                return true;
            }
        }
        return false;
    }

    // Implementamos el método para realizar un viaje
    @Override
    public void realizarViaje(ViajeInterplanetario viajeInterplanetario) throws NaveEspacialException {
        if (super.contarTripulantes() == 0) {
            throw new NaveEspacialException("La nave no tiene tripulantes");
        }
        if (!super.hayPiloto()) {
            throw new NaveEspacialException("La nave no puede despegar sin un piloto");
        }
        if (viajeInterplanetario.isRadiactivo() && !super.isDefensaRadiacion()) {
            throw new NaveEspacialException("La nave no tiene protección contra alta radiación y el viaje puede ser peligroso");
        }
        if (!haySensores()) {
            throw new NaveEspacialException("Una nave de exploración no puede despegar sin al menos un sensor");
        }

        boolean hayEspacio = false;
        for (int i = 0; i < super.getViajesInterplanetarios().length; i++) {
            if (super.getViajesInterplanetarios()[i] == null) {
                hayEspacio = true;
                super.getViajesInterplanetarios()[i] = viajeInterplanetario;
                System.out.println("Viaje de la nave " + super.getNombre() + " en una misión " +
                        super.getViajesInterplanetarios()[i].getMision() + " hacia " +
                        super.getViajesInterplanetarios()[i].getDestino() + " con unas horas de llegada de " +
                        super.getViajesInterplanetarios()[i].getHorasLlegada());
                break;
            }
        }
        if (!hayEspacio) {
            throw new NaveEspacialException("La nave no puede realizar más viajes");
        }
        if ((super.getSalud() - 10) < 1) {
            super.setSalud(1);

        } else {
            super.setSalud(super.getSalud() - 10);
        }
        super.quitarVidaTripulantes();
    }

    // Hacemos un método para añadir un sensor a la nave de exploración
    public void anadirSensor(Sensor sensorAMeter) throws NaveEspacialException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < sensores.length; i++) {
            if (sensorAMeter.equals(sensores[i])) {
                esIgual = true;
            }
            if (sensores[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            sensores[hayEspacio] = sensorAMeter;

        } else {
            throw new NaveEspacialException("No se ha podido añadir la nave espacial");
        }
    }

    // Implementamos el método de la interfaz 'Auxiliable'
    @Override
    public void pedirAuxilio(NaveEspacial naveEspacial) throws NaveEspacialException {
        if (naveEspacial == null) {
            throw new NaveEspacialException("No se ha encontrado una nave a la que pedirle auxilio");
        }
        if (this.equals(naveEspacial)) {
            throw new NaveEspacialException("No te puedes pedir ayuda a ti mismo");
        }
        boolean encontrada = false;
        for (int i = 0; i < FlotaApp.navesEspaciales.length; i++) {
            if (naveEspacial.equals(FlotaApp.navesEspaciales[i])) {
                encontrada = true;
                System.out.println("La nave " + super.getNombre() + " ha pedido auxilio a " + FlotaApp.navesEspaciales[i].getNombre());
                break;
            }
        }
        if (!encontrada) {
            throw new NaveEspacialException("La nave no se encuentra en la flota");
        }
    }
}