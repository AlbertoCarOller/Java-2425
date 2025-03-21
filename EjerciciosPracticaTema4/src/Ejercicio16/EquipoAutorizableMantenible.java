package Ejercicio16;

public class EquipoAutorizableMantenible extends Equipo implements Autorizable, Mantenible {

    // Creamos las características
    private boolean autorizacion;
    private boolean perfecto;

    // Creamos el constructor
    public EquipoAutorizableMantenible(String descripcion, int valor, boolean autorizacion, boolean perfecto) {
        super(descripcion, valor);
        this.autorizacion = autorizacion;
        this.perfecto = perfecto;
    }

    // Creamos los get y set
    public boolean isAutorizacion() {
        return autorizacion;
    }

    protected void setAutorizacion(boolean autorizacion) {
        this.autorizacion = autorizacion;
    }

    public boolean isPerfecto() {
        return perfecto;
    }

    protected void setPerfecto(boolean perfecto) {
        this.perfecto = perfecto;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + ", Autorización: " + this.autorizacion + ", Perfecto: " + this.perfecto;
    }

    @Override
    public void autorizar() throws EquipoException {
        if (autorizacion) {
            throw new EquipoException("El equipo con id" + super.getId() + " ya está autorizado");
        }
        this.autorizacion = true;
        super.setValor(super.getValor() + 5);
        System.out.println("El equipo con id " + super.getId() + " ha sido autorizado");
    }

    @Override
    public void reparar() throws EquipoException {
        if (perfecto) {
            throw new EquipoException("El equipo con id " + super.getId() + " no se puede reparar");
        }
        this.perfecto = true;
        super.setValor(super.getValor() + 2);
        System.out.println("El equipo con id " + super.getId() + " ha sido reparado");
    }
}