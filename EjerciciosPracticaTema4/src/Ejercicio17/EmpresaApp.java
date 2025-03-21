package Ejercicio17;

import java.util.ArrayList;
import java.util.List;

public class EmpresaApp {
    static List<Juego> juegos = new ArrayList<>();
    static String nombreEmpresa = "Chelu´s estudios";

    public static void main(String[] args) {
        try {
            // Creamos juegos y los añadimos
            JuegoAccion juegoAccion = new JuegoAccion("Chelu videogame", nombreEmpresa, 35.6);
            JuegoRol juegoRol = new JuegoRol("Chelu´s rol", nombreEmpresa, 20, Complejidad.MEDIO);
            JuegoEstrategia juegoEstrategia = new JuegoEstrategia("Chelu´s estrategia", nombreEmpresa, 23.5, 8);
            JuegoDeporte juegoDeporte = new JuegoDeporte("Chelu´s sport", nombreEmpresa, 12,
                    TDeporte.DE_CAMPO, "FIFA");
            juegos.add(juegoAccion);
            juegos.add(juegoRol);
            juegos.add(juegoEstrategia);
            juegos.add(juegoDeporte);
            llamarMetodos();

        } catch (JuegoException e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método para llamar a los métodos dependiendo de la interfaz que implemente
    public static void llamarMetodos() {
        for (Juego juego : juegos) {
            if (juego instanceof Actualizable actualizable) {
                actualizable.actualizar();
            }
            if (juego instanceof Multijugable multijugable) {
                multijugable.conectarAServidor();
            }
            System.out.println("El total de " + juego.getTitulo() + " es de " + juego.calcularCostoTotal());
        }
    }
}