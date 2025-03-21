package Ejercicio8;

public class ViajeInterplanetario {

    // Creamos los atributos
    private Destino destino;
    private int horasLlegada;
    private Mision mision;
    private boolean radiactivo;

    // Creamos los atributos
    public ViajeInterplanetario(Destino destino, int horasLlegada, Mision mision, boolean radiactivo) throws NaveEspacialException {
        this.destino = destino;
        setHorasLlegada(horasLlegada);
        this.mision = mision;
        this.radiactivo = radiactivo;
    }

    // Creamos los get y set
    public Destino getDestino() {
        return destino;
    }

    private void setDestino(Destino destino) {
        this.destino = destino;
    }

    public int getHorasLlegada() {
        return horasLlegada;
    }

    private void setHorasLlegada(int horasLlegada) throws NaveEspacialException {
        if (horasLlegada < 1 || horasLlegada > 24) {
            throw new NaveEspacialException("Las horas de llegada a una misión no puede ser menos de 1 hora ni más de 24 horas");
        }
        this.horasLlegada = horasLlegada;
    }

    public Mision getMision() {
        return mision;
    }

    private void setMision(Mision mision) {
        this.mision = mision;
    }

    public boolean isRadiactivo() {
        return radiactivo;
    }

    private void setRadiactivo(boolean radiactivo) {
        this.radiactivo = radiactivo;
    }

    // Hacemos un toStringç
    @Override
    public String toString() {
        return String.format("El destino es %s, las horas de llegada es de %d y la misión es de tipo %s. Radiactividad: %b", this.destino,
                this.horasLlegada, this.mision, this.radiactivo);
    }
}