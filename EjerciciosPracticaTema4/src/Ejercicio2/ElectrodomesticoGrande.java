package Ejercicio2;

public class ElectrodomesticoGrande extends Electrodomestico implements Conectable {

    // Creamos los atributos
    private Energia energia;
    private int numPuertas;

    // Hacemos el constructor
    public ElectrodomesticoGrande(String nombre, String marca, double precio, Energia energia, int numPuertas) throws ElectrodomesticoException {
        super(nombre, marca, precio);
        this.energia = energia;
        setNumPuertas(numPuertas);
    }

    // Hacemos los get y set
    public Energia getEnergia() {
        return energia;
    }

    private void setEnergia(Energia energia) {
        this.energia = energia;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    private void setNumPuertas(int numPuertas) throws ElectrodomesticoException {
        if (numPuertas < 1) {
            throw new ElectrodomesticoException("No puede tener menos de 1 puerta");
        }
        this.numPuertas = numPuertas;
    }

    // Implementamos los métodos
    @Override
    public void conectar() {
        System.out.println("Conectando " + super.getNombre());
    }

    @Override
    public void desconectar() {
        System.out.println("Desconectando " + super.getNombre());
    }
}