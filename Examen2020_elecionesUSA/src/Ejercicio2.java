public class Ejercicio2 {

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

    private static void recorrerJardin() {

        for (int i = 0; i < jardin.length; i++) {

            for (int j = 0; j < jardin[i].length; j++) {

                if (jardin[i][j] == 1) {

                    boolean agua = agua(i, j);
                    boolean abeja = abeja(i, j);
                    boolean malaHierba = malaHierba(i, j);
                    boolean gusanoComeHierba = gusanoComoHierba(i, j);

                    if (agua && abeja && !malaHierba) {

                        System.out.println("Florece en la posición " + i + ", " + j);

                    } else if (agua && abeja && gusanoComeHierba) {

                        System.out.println("Florece en la posición " + i + ", " + j);

                    } else {

                        System.out.println("No florece en la posición " + i + ", " + j);

                    }
                }
            }
        }
    }

    private static boolean agua(int i, int j) {

        boolean agua = false;

        for (int k = i - 1; k <= i + 1; k++) {

            for (int l = j - 1; l <= j + 1; l++) {

                if (k >= 0 && k < jardin.length && l >= 0 && l < jardin[k].length) {

                    if (jardin[k][l] == 2) {

                        agua = true;
                    }
                }
            }
        }
        return agua;
    }

    private static boolean abeja(int i, int j) {

        boolean abeja = false;

        for (int k = i - 3; k <= i + 3; k++) {

            for (int l = j - 3; l <= j + 3; l++) {

                if (k >= 0 && k < jardin.length && l >= 0 && l < jardin[k].length) {

                    if (jardin[k][l] == 5) {

                        abeja = true;
                    }
                }
            }
        }
        return abeja;
    }

    private static boolean malaHierba(int i, int j) {

        boolean malaHierba = false;

        for (int k = i - 1; k <= i + 1; k++) {

            for (int l = j - 1; l <= j + 1; l++) {

                if (k >= 0 && k < jardin.length && l >= 0 && l < jardin[k].length) {

                    if (jardin[k][l] == 3) {

                        malaHierba = true;
                    }
                }
            }
        }
        return malaHierba;
    }

    private static boolean gusanoComoHierba(int i, int j) {

        boolean gusanoComeHierba = false;

        for (int k = i - 1; k <= i + 1; k++) {

            for (int l = j - 1; l <= j + 1; l++) {

                if (k >= 0 && k < jardin.length && l >= 0 && l < jardin[k].length) {

                    if (jardin[k][l] == 4) {

                        gusanoComeHierba = true;
                    }
                }
            }
        }
        return gusanoComeHierba;
    }
}