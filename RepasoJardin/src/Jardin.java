public class Jardin {

    // Creamos el jardín de 15x15
    static int[][] jardin = {
            {1, 0, 0, 0, 1, 0, 0, 0, 2, 1, 1, 0, 0, 5, 0},
            {0, 2, 0, 5, 0, 0, 3, 0, 1, 0, 0, 0, 1, 0, 0},
            {3, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2, 4},
            {0, 0, 2, 0, 0, 0, 0, 0, 2, 1, 3, 0, 0, 0, 1},
            {0, 0, 1, 0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 0, 3},
            {4, 3, 0, 0, 0, 2, 1, 0, 0, 0, 0, 5, 0, 0, 0},
            {1, 2, 0, 5, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {3, 0, 0, 0, 0, 0, 2, 1, 4, 0, 0, 0, 1, 0, 0},
            {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 0},
            {0, 0, 5, 1, 0, 0, 0, 5, 0, 3, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 5, 2, 4, 0},
            {0, 0, 1, 3, 0, 0, 1, 0, 0, 1, 0, 0, 1, 3, 0},
            {0, 1, 2, 1, 0, 0, 0, 0, 0, 2, 3, 1, 0, 0, 1},
            {1, 3, 4, 0, 0, 5, 0, 0, 1, 0, 0, 0, 0, 4, 2}
    };

    public static void main(String[] args) {

        recorrerJardin();
    }

    // Recorremos el jardín para posteriormente mirar las casillas adyacentes
    public static void recorrerJardin() {

        for (int i = 0; i < jardin.length; i++) {

            for (int j = 0; j < jardin[i].length; j++) {

                // Si encontramos una flor, llamamos a los métodos
                if (jardin[i][j] == 1) {

                    boolean agua = hayAgua(i, j);
                    boolean abeja = hayAbeja(i, j);
                    boolean malaHierba = hayMalaHierba(i, j);
                    boolean gusanoComeHierba = hayGusanoComoHierba(i, j);

                    if (agua && abeja && gusanoComeHierba) {

                        System.out.println("Florecerá en la posición " + i + ", " + j);

                    } else if (agua && abeja && !malaHierba) {

                        System.out.println("Florecerá en la posición " + i + ", " + j);
                    }
                }
            }
        }
    }

    // Recorremos la sub matríz en busca de agua
    public static boolean hayAgua(int i, int j) {

        for (int k = i - 1; k <= i + 1; k++) {

            for (int l = j - 1; l <= j + 1; l++) {

                if (i == k && j == l) {

                    continue;
                }

                if (k >= 0 && k < jardin.length && l >= 0 && l < jardin[i].length) {

                    if (jardin[k][l] == 2) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Recorremos la sub matríz con un radio más grande en busca de abejas
    public static boolean hayAbeja(int i, int j) {

        for (int k = i - 3; k <= i + 3; k++) {

            for (int l = j - 3; l <= j + 3; l++) {

                if (i == k && j == l) {

                    continue;
                }
                if (k >= 0 && k < jardin.length && l >= 0 && l < jardin[i].length) {

                    if (jardin[k][l] == 5) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Recorremos la sub matríz en busca de mala hierba
    public static boolean hayMalaHierba(int i, int j) {

        for (int k = i - 1; k <= i + 1; k++) {

            for (int l = j - 1; l <= j + 1; l++) {

                if (i == k && j == l) {

                    continue;
                }

                if (k >= 0 && k < jardin.length && l >= 0 && l < jardin[i].length) {

                    if (jardin[k][l] == 3) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Recorremos la sub matríz en busca de un gusano
    public static boolean hayGusanoComoHierba(int i, int j) {

        for (int k = i - 1; k <= i + 1; k++) {

            for (int l = j - 1; l <= j + 1; l++) {

                if (i == k && j == l) {

                    continue;
                }

                if (k >= 0 && k < jardin.length && l >= 0 && l < jardin[i].length) {

                    if (jardin[k][l] == 4) {

                        return true;
                    }
                }
            }
        }
        return false;
    }
}