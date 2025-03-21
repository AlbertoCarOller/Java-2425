public abstract class JuguetePlastico extends  Juguete {

    // Creamos los atributos
    private Plastico tipoPlastico;

    // Creamos el constructor
    public JuguetePlastico(String nombre, String marca, Plastico tipoPlastico) {
        super(nombre, marca);
        this.tipoPlastico = tipoPlastico;
    }

    // Hacemos los get y set
    public Plastico getTipoPlastico() {
        return tipoPlastico;
    }

    private void setTipoPlastico(Plastico tipoPlastico) {
        this.tipoPlastico = tipoPlastico;
    }
}