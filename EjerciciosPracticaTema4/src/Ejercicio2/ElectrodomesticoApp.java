package Ejercicio2;

public class ElectrodomesticoApp {
    public static void main(String[] args) {

        try {
            // Creamos varios electrodomesticos
            Programable cafetera = new ElectrodomesticoPequeno("Cafetera 3000", "Atisbedo", 23.70,
                    6, Corriente.ELECTRICO);
            Conectable secadora = new ElectrodomesticoGrande("Secadora 7000", "Respicio", 98.60,
                    Energia.ELECTRICO, 1);

            // Llamamos a sus métodos
            cafetera.programar(15);
            secadora.conectar();
            secadora.desconectar();

        } catch (ElectrodomesticoException e) {
            System.out.println(e.getMessage());
        }
    }
}