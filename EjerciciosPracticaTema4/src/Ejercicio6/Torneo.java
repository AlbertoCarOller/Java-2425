package Ejercicio6;

public class Torneo {
    public static void main(String[] args) {
        try {
            Lucha lucha1 = new Lucha();
            Luchador luchador1 = new Medico("Atisbedo", 10, 20, 15, 50);
            Luchador luchador2 = new Atacante("Respicio Godefrío", 3, 20, 10, 70);
            lucha1.anadirLuchador(luchador1);
            lucha1.anadirLuchador(luchador2);
            lucha1.comenzarLucha();

        } catch (LuchadorException e) {
            System.out.println(e.getMessage());
        }
    }
}
