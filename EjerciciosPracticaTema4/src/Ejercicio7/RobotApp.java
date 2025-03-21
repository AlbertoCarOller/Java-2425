package Ejercicio7;

public class RobotApp {
    public static void main(String[] args) {
        try {
            // Creamos la carrera
            Carrera carrera = new Carrera(10, "Competición galáctica de robots");
            // Creamos los robots
            RobotHibrido robotHibrido = new RobotHibrido("Atisbedo", 20, 20);
            RobotAereo robotAereo = new RobotAereo("Respicio Godefrío", 15, 30);
            RobotTerrestre robotTerrestre = new RobotTerrestre("Bermubot", 30, 10);
            RobotAereo robotAereo2 = new RobotAereo("Carles Xavier", 5, 35);
            carrera.setRobots(new Robot[]{robotHibrido, robotAereo, robotTerrestre, robotAereo2});
            // Creamos y añadimos los obstáculos
            Obstaculo obstaculo1 = Obstaculo.CRATERES_PROFUNDOS;
            Obstaculo obstaculo2 = Obstaculo.TERRENO_INESTABLE;
            Obstaculo obstaculo3 = Obstaculo.CAMPO_MAGNETICO;
            Obstaculo obstaculo4 = Obstaculo.TORMETA_DE_ARENA;
            carrera.setObstaculos(new Obstaculo[]{obstaculo1, obstaculo2, obstaculo3, obstaculo4});
            carrera.comenzarCarrera();

        } catch (RobotException e) {
            System.out.println(e.getMessage());
        }
    }
}