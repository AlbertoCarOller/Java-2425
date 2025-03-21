package Extra.Ejercicio7;

public enum Prioridad {
    BAJA(3), MEDIA(2), ALTA(1);

    final int valorNumerico;

    Prioridad(int valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    // Hacemos un get
    public int getValorNumerico() {
        return valorNumerico;
    }
}