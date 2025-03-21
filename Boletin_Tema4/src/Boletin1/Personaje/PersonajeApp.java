package Boletin1.Personaje;

public class PersonajeApp {
    public static void main(String[] args) {

        try {
            // Creamos un mago A
            Mago magoA = new Mago("Alfredo", Raza.ELFO, 14, 18, 75, 40);
            magoA.aprendeHechizo("bailus");
            magoA.aprendeHechizo("gargantus");

            // Creamos un mago B
            Mago magoB = new Mago("Petalos", Raza.ORCO, 14, 19, 65, 35);
            magoB.aprendeHechizo("cuchurrufleto");

            // Imprimos los datos de los magos
            System.out.println(magoA);
            System.out.println(magoB);

            // Creamos un clérigo
            Clerigo clerigo = new Clerigo("Atisbedo", Raza.HUMANO, 19, 14, 30,
                    9, "Respicio Godefrio");

            // Probamos los métodos de lanzar hechizo y curar
            magoA.lanzarHechizo(magoB, "bailus");
            magoB.lanzarHechizo(magoA, "cuchurrufleto");
            clerigo.curar(magoB);
            magoA.lanzarHechizo(magoB, "gargantus");

            // Imprimimos los datos de los objetos
            System.out.println(magoA);
            System.out.println(magoB);
            System.out.println(clerigo);

        } catch (PersonaException e) {
            System.out.println(e.getMessage());
        }
    }
}