package Ejercicio15;

public class TallerApp {
    public static void main(String[] args) {
        try {
            Taller taller = new Taller("Taller Chelu");
            Pieza piezaBasica1 = new Pieza("Pieza1", 30);
            PiezaReparable piezaReparable1 = new PiezaReparable("PiezaReparable1", 40, false);
            PiezaReparableTesteable piezaReparableTesteable1 = new PiezaReparableTesteable("PiezaReparableTesteable1", 20, false);
            PiezaTesteable piezaTesteable1 = new PiezaTesteable("Pieza Testeable1", 30);
            taller.registrarPieza(piezaBasica1);
            taller.registrarPieza(piezaReparable1);
            taller.registrarPieza(piezaReparableTesteable1);
            taller.registrarPieza(piezaTesteable1);
            comprobarTipoPieza(taller);
        } catch (TallerExeption e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método que va a comprobar el tipo de pieza de todas las piezas del taller y llamar a sus métodos
    public static void comprobarTipoPieza(Taller taller) throws TallerExeption {
        for (int i = 0; i < taller.getPiezas().length; i++) {
            if (taller.getPiezas()[i] instanceof PiezaTesteable piezaTesteable) {
                boolean pasa = piezaTesteable.testear();
                if (pasa) {
                    System.out.println(piezaTesteable.getNombre() + " ha pasado el testeo");

                } else {
                    System.out.println(piezaTesteable.getNombre() + " no ha pasado el testeo");
                }
            }
            if (taller.getPiezas()[i] instanceof PiezaReparable piezaReparable) {
                piezaReparable.reparar();
            }
            if (taller.getPiezas()[i] instanceof PiezaReparableTesteable piezaReparableTesteable) {
                piezaReparableTesteable.reparar();
                boolean pasa = piezaReparableTesteable.testear();
                if (pasa) {
                    System.out.println(piezaReparableTesteable.getNombre() + " ha pasado el testeo");

                } else {
                    System.out.println(piezaReparableTesteable.getNombre() + " no ha pasado el testeo");
                }
            }
        }
    }
}