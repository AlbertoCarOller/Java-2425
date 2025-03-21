package Boletin1.InstalacionDeportiva;

public class Oficina implements Edificio{

    private double superficie;

    public Oficina(double superficie) {
        this.superficie = superficie;
    }

    @Override
    public double getSuperficieEdificio() {
        return superficie;
    }
}