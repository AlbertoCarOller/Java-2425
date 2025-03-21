package Extra.Ejercicio1;

public enum Prioridad {
    ALTA(1), MEDIA(2), BAJA(3);

    private int prioridad;

    Prioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getPrioridad() {
        return prioridad;
    }
}