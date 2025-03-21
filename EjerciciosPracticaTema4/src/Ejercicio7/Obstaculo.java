package Ejercicio7;

public enum Obstaculo {
    TORMETA_DE_ARENA(7, "Tormeta de Arena"), CRATERES_PROFUNDOS(15, "Crateres Profundos"),
    CAMPO_MAGNETICO(5, "Campo Magnético"), TERRENO_INESTABLE(5, "Terreno Inestable");

    // Creamos el atributo
    private int obstaculo;
    private String nombre;

    // Creamos el constructor
    Obstaculo(int i, String nombre) {
        this.obstaculo = i;
        this.nombre = nombre;
    }

    // Hacemos el get
    public int getObstaculo() {
        return obstaculo;
    }

    public String getNombre() {
        return nombre;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Obstaculo{" +
                "obstaculo=" + obstaculo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}