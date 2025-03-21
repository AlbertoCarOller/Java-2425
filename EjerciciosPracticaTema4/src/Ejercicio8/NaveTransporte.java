package Ejercicio8;

public class NaveTransporte extends NaveEspacial {

    // Hacemos los atributos
    private final int MAX_MERCANCIA;

    // Creamos el constructor
    public NaveTransporte(String nombre, int horasAutonomiaEnergetic, Combustible combustible, int CAPACIDAD_MAX, int MAX_MERCANCIA, boolean terraformado, boolean defensaRadiacion) throws NaveEspacialException {
        super(nombre, horasAutonomiaEnergetic, combustible, CAPACIDAD_MAX, terraformado, defensaRadiacion);
        if (CAPACIDAD_MAX < 100 || CAPACIDAD_MAX > 5000) {
            throw new NaveEspacialException("Las naves de transporte no pueden llevar menos de 100 personas ni más de 5000");
        }
        if (MAX_MERCANCIA < 100 || MAX_MERCANCIA > 5000) {
            throw new NaveEspacialException("Las naves de transporte no pueden llevar menos de 100 de carga ni más de 5000");
        }
        this.MAX_MERCANCIA = MAX_MERCANCIA;
    }

    // Hacemos los get y set
    public int getMAX_MERCANCIA() {
        return MAX_MERCANCIA;
    }

    protected void setHorasAutonomiaEnergetica(int horasAutonomiaEnergetica) throws NaveEspacialException {
        if (horasAutonomiaEnergetica < 12 || horasAutonomiaEnergetica > 48) {
            throw new NaveEspacialException("Las naves de transporte no pueden tener menos de 12 horas de autonomía ni más de 48");
        }
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + ",Máxima mercancía=" + this.MAX_MERCANCIA;
    }

    // Implementamos el método para realizar un viaje
    @Override
    public void realizarViaje(ViajeInterplanetario viajeInterplanetario) throws NaveEspacialException {
        // En caso de que no hayan tripulantes saltará la excepción
        if (super.contarTripulantes() == 0) {
            throw new NaveEspacialException("No se puede realizar el viaje ya que no hay tripulantes en la nave");
        }
        if (!super.hayPiloto()) {
            throw new NaveEspacialException("La nave no puede despegar sin un piloto");
        }
        if (viajeInterplanetario.isRadiactivo() && !super.isDefensaRadiacion()) {
            throw new NaveEspacialException("No puedes realizar un viaje que contiene una amenaza de radiación porque tu nave no está protegida");
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
}