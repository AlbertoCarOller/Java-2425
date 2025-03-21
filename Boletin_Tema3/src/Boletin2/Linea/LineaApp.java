package Boletin2.Linea;

import java.util.Scanner;

public class LineaApp {
    public static void main(String[] args) {

        // Creamos el scanner
        Scanner sc = new Scanner(System.in);

        // Creamos los puntos
        Punto puntoA = new Punto();
        Punto puntoB = new Punto();

        // Creamos una segunda línea con sus puntos
        Punto puntoA2 = new Punto(1.2, 4.6);
        Punto puntoB2 = new Punto(3.5, 6.7);
        Linea ln2 = new Linea(puntoA2, puntoB2);

        // Solicitamos el eje x e y de 'puntoA'
        System.out.println("Introduce el eje x del punto A");
        double ejeXPuntoA = sc.nextDouble();
        sc.nextLine();
        puntoA.setX(ejeXPuntoA);

        System.out.println("Introduce el eje y del punto A");
        double ejeYPuntoA = sc.nextDouble();
        sc.nextLine();
        puntoA.setY(ejeYPuntoA);


        // Solicitamos el eje x e y de 'puntoB'
        System.out.println("Introduce el eje x del punto B");
        double ejeXPuntoB = sc.nextDouble();
        sc.nextLine();
        puntoB.setX(ejeXPuntoB);

        System.out.println("Introduce el eje y del punto B");
        double ejeYPuntoB = sc.nextDouble();
        sc.nextLine();
        puntoB.setY(ejeYPuntoB);

        // Creamos la linea
        Linea ln = new Linea(puntoA, puntoB);

        String op;

        do {
            System.out.println("a.Mover línea");
            System.out.println("b.Mostrar línea");
            System.out.println("c.Salir");

            op = sc.nextLine();

            try {
                switch (op) {

                    case "a":
                        System.out.println("Hacía donde quiere mover (A, B, I o D)");
                        String op2 = sc.nextLine();

                        switch (op2) {

                            case "A":
                                System.out.println("¿Cuánta distancia la movemos hacia arriba?");
                                double distanciaArriba = sc.nextDouble();
                                sc.nextLine();
                                ln.moverArriba(distanciaArriba);
                                break;

                            case "B":
                                System.out.println("¿Cuánta distancia la movemos hacia abajo?");
                                double distanciaAbajo = sc.nextDouble();
                                sc.nextLine();
                                ln.moverAbajo(distanciaAbajo);
                                break;

                            case "I":
                                System.out.println("¿Cuánta distancia la momemos hacia la izquierda?");
                                double distanciaIzquierda = sc.nextDouble();
                                sc.nextLine();
                                ln.moverIzquierda(distanciaIzquierda);
                                break;

                            case "D":
                                System.out.println("¿Cuánta distancia la quieres mover hacia la derecha?");
                                double distanciaDerecha = sc.nextDouble();
                                sc.nextLine();
                                ln.moverDerecha(distanciaDerecha);
                                break;

                            default:
                                System.out.println("No has elegido ninguna opción");
                                break;
                        }

                        break;

                    case "b":
                        System.out.println(ln);
                        break;

                    case "c":
                        System.out.println("Hasta pronto");
                        break;

                    default:
                        System.out.println("No has seleccionado nada");
                        break;
                }
            } catch (LineaException e) {
                System.out.println(e.getMessage());
            }

        } while (!op.equals("c"));

        // Comparamos la línea del usuario con la introducida por parámetros
        if (ln.equals(ln2)) {

            System.out.println("Las lineas son iguales");
        } else {

            System.out.println("Las lineas son ditintas");
        }
    }
}