package Ejercicio7;

import java.util.Arrays;

public class Carrera {

    // Creamos los atributos
    private String nombre;
    private final int FINAL;
    private Robot[] robots;
    private Obstaculo[] obstaculos;

    // Creamos el constructor
    public Carrera(int FINAL, String nombre) throws RobotException {
        if (FINAL < 1) {
            throw new RobotException("La carrera no puede tener menos de 1 km");
        }
        this.FINAL = FINAL;
        this.nombre = nombre;
        this.robots = new Robot[5];
        this.obstaculos = new Obstaculo[4];
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFINAL() {
        return FINAL;
    }

    public Robot[] getRobots() {
        return robots;
    }

    protected void setRobots(Robot[] robots) {
        this.robots = robots;
    }

    // Hacemos los get y set
    public Obstaculo[] getObstaculos() {
        return obstaculos;
    }

    protected void setObstaculos(Obstaculo[] obstaculos) {
        this.obstaculos = obstaculos;
    }

    // Hacemos un método para iniciar la carrera hasta que gane algún robot gane
    public void comenzarCarrera() throws RobotException {
        boolean primerAceleron = false;
        boolean haGanado = false;
        while (!haGanado) {
            for (int i = 0; i < robots.length && !haGanado; i++) {
                if (robots[i] == null) {
                    continue;
                }
                if (!primerAceleron) {
                    robots[i].acelerar();
                }
                int obstaculoAparecido = (int) (Math.random() * obstaculos.length);
                haGanado = robots[i].superarObstaculo(obstaculos[obstaculoAparecido], FINAL);
                if (haGanado) {
                    System.out.println(robots[i].getNombre() + " ha ganado");

                } else {
                    System.out.println(robots[i].getNombre() + " ha superado el obstáculo " + obstaculos[i].getNombre());
                }
            }
            primerAceleron = true;
        }
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Carrera{" +
                "nombre='" + nombre + '\'' +
                ", FINAL=" + FINAL +
                ", robots=" + Arrays.toString(robots) +
                ", obstaculos=" + Arrays.toString(obstaculos) +
                '}';
    }
}