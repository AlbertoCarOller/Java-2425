package Ejercicio16;

public class EquipoAutorizable extends Equipo implements Autorizable {

    // Creamos las características
    private boolean autorizacion;

    // Creamos el constructor
    public EquipoAutorizable(String descripcion, int valor, boolean autorizacion) {
        super(descripcion, valor);
        this.autorizacion = autorizacion;
    }

    // Hacemos los get y set
    public boolean isAutorizacion() {
        return autorizacion;
    }

    protected void setAutorizacion(boolean autorizacion) {
        this.autorizacion = autorizacion;
    }

    // Hacemos un toString modificado
    @Override
    public String toString() {
        return super.toString() + ", Autorización: " + this.autorizacion;
    }

    @Override
    public void autorizar() throws EquipoException {
        if (autorizacion) {
            throw new EquipoException("Ya está autorizado");
        }
        this.autorizacion = true;
        super.setValor(super.getValor() + 5);
        System.out.println("El equipo con id " + super.getId() + " ha sido autorizado");
    }
}