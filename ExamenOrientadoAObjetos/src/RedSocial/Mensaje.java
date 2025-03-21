package RedSocial;

public class Mensaje {

    // Creamos los atributos
    private static final int MAX_TAMANO_MENSAJE = 150;
    private String cuerpo;
    private boolean publico;
    private int meGustas;

    // Creamos un constructor
    public Mensaje(String cuerpo, boolean publico) throws MensajeException {
        setCuerpo(cuerpo);
        this.publico = publico;
        this.meGustas = 0;
    }

    // Hacemos los get y set
    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) throws MensajeException {

        if (cuerpo.length() > MAX_TAMANO_MENSAJE) {
            throw new MensajeException("El mensaje no puede sobrepasar el límite de 150");
        }
        this.cuerpo = cuerpo;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public int getMeGustas() {
        return meGustas;
    }

    public void setMeGustas(int meGustas) {
        this.meGustas = meGustas;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Mensaje{" +
                "cuerpo='" + cuerpo + '\'' +
                ", publico=" + publico +
                ", meGustas=" + meGustas +
                '}';
    }
}