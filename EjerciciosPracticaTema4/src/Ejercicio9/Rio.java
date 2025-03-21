package Ejercicio9;

public class Rio extends ElementoPaisaje {

    // Creamos los atributos
    private Agua agua;
    private int caudal;
    private boolean cataratas;

    // Creamos el constructor
    public Rio(String nombre, Agua agua, int caudal, boolean cataratas) {
        super("Río " + nombre, 70);
        this.agua = agua;
        this.caudal = caudal;
        this.cataratas = cataratas;
    }

    // Hacemos los get y set
    public Agua getAgua() {
        return agua;
    }

    private void setAgua(Agua agua) {
        this.agua = agua;
    }

    public int getCaudal() {
        return caudal;
    }

    private void setCaudal(int caudal) throws ElementoException {
        if (caudal < 1 || caudal > 50) {
            throw new ElementoException("El río no puede tener un caudal menor a 1 ni mayor a 50");
        }
        this.caudal = caudal;
    }

    public boolean isCataratas() {
        return cataratas;
    }

    private void setCataratas(boolean cataratas) {
        this.cataratas = cataratas;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + "\nAgua: " + this.agua + "\nCaudal: " + this.caudal + "\nCataratas: " + this.cataratas;
    }
}