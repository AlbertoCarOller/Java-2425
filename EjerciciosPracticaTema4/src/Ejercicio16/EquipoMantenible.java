package Ejercicio16;

public class EquipoMantenible extends Equipo implements Mantenible {

    // Creamos las caracteristicas
    private boolean perfecto;

    // Creamos el constructor
    public EquipoMantenible(String descripcion, int valor, boolean perfecto) {
        super(descripcion, valor);
        this.perfecto = perfecto;
    }

    // Hacemos los get y set
    public boolean isPerfecto() {
        return perfecto;
    }

    protected void setPerfecto(boolean perfecto) {
        this.perfecto = perfecto;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + ", Perfecto: " + this.perfecto;
    }

    @Override
    public void reparar() throws EquipoException {
        if (this.perfecto) {
            throw new EquipoException("El equipo ya está perfecto");
        }
        this.perfecto = true;
        super.setValor(super.getValor() + 2);
        System.out.println("El equipo con id " + super.getId() + " ha sido reparado");
    }
}