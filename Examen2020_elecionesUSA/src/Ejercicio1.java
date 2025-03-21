public class Ejercicio1 {
    public static void main(String[] args) {

        // Declaramos el primer array con los estados de Estados Unidos
        String[] estados = {"Alabama", "Alaska", "Arizona", "Arkansas", "California",
                "Carolina del Norte", "Carolina del Sur", "Colorado", "Connecticut", "Dakota del Norte",
                "Dakota del Sur", "Delaware", "Distrito de Columbia", "Florida", "Georgia", "Hawái",
                "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Luisiana", "Maine", "Maryland",
                "Massachusetts", "Míchigan", "Minnesota", "Mississippi", "Misuri", "Montana", "Nebraska", "Nevada",
                "Nueva Jersey", "Nueva York", "Nuevo Hampshire", "Nuevo México", "Ohio", "Oklahoma", "Oregón",
                "Pensilvania", "Rhode Island", "Tennessee", "Texas", "Utah", "Vermont", "Virginia",
                "Virginia Occidental", "Washington", "Wisconsin", "Wyoming"};

        // Declaramos el segundo array con los electores
        int[] electores = {9, 3, 11, 6, 55, 15, 9, 9, 7, 3, 3, 3, 3, 29, 16, 4, 4, 20, 11, 6, 6, 8, 8, 4, 10, 11,
                16, 10, 6, 10, 3, 5, 6, 14, 29, 4, 5, 18, 7, 7, 20, 4, 11, 38, 6, 3, 13, 5, 12, 10, 3};

        // Declaramos el tercer array con los votos de los demócratas
        int[] votosDemocratas = {729547, 116454, 1161167, 380494, 8753788, 1338870, 897572, 235603, 282830, 4504975,
                1877963, 266891, 189765, 3090729, 1033126, 653669, 427005, 628854, 780154, 715326, 1677928, 1995196,
                2268839, 1367716, 485131, 1071068, 177709, 568988, 539260, 348526, 2148278, 385234, 4556124, 2189316,
                93758, 2394164, 420375, 1002106, 2926441, 252525, 855373, 117458, 870695, 3877868, 310676, 178573,
                1981473, 1742718, 188794, 1382536, 55973};

        // Declaramos el cuarto y último array con los votos de los republicanos
        int[] votosRepublicanos = {1318255, 163387, 1252401, 684872, 4483810, 1202484, 673215, 185127, 12723, 4617886,
                2089104, 128847, 409055, 2146015, 1557286, 800983, 671018, 1202971, 1178638, 671154, 943169, 1090893,
                2279543, 1322951, 700714, 1594511, 279240, 991922, 512058, 345790, 1601933, 319667, 2819534, 2362631,
                216794, 2841005, 949136, 782403, 2970733, 180543, 1155389, 227721, 1522925, 4685047, 515231, 95369,
                1769443, 1221747, 489371, 1405284, 174419};

        // Llamamos al método para saber que partido ha ganado las elecciones
        partidoDelPresidenteElecto(votosDemocratas, votosRepublicanos, electores);

        // Llamamos al método para contar el número de electores de cada partido
        electoresTotales(votosDemocratas, votosRepublicanos, electores);

        // Llamamos al método que me da los puntos totales de cada partido y cuál es el más votado
        partidoConMasPuntos(votosDemocratas, votosRepublicanos);

        // Llamamos al método que cuenta el número de estados que apoya cada partido
        estadosQueApoyanACadaPartido(votosDemocratas, votosRepublicanos);

        // Llamamos al método que nos dice el estado con más electores y cuántos electores tiene
        estadoConMasElectores(votosDemocratas, votosRepublicanos, electores, estados);
    }

    private static void partidoDelPresidenteElecto(int[] votosDemocratas, int[] votosRepublicanos, int[] electores) {

        int democratas = 0;
        int republicanos = 0;

        for (int i = 0; i < votosDemocratas.length; i++) {


            if (votosDemocratas[i] > votosRepublicanos[i]) {

                democratas += electores[i];

            } else if (votosRepublicanos[i] > votosDemocratas[i]) {

                republicanos += electores[i];
            }
        }

        if (democratas > republicanos) {

            System.out.println("El presidente electo es del partido demócrata");

        } else {

            System.out.println("El presidente electo es del partido republicano");
        }
    }

    // Método para contar el número de electores de cada partido
    private static void electoresTotales(int[] votosDemocratas, int[] votosRepublicanos, int[] electores) {

        int democratas = 0;
        int republicanos = 0;

        for (int i = 0; i < votosDemocratas.length; i++) {

            if (votosDemocratas[i] > votosRepublicanos[i]) {

                democratas += electores[i];

            } else if (votosRepublicanos[i] > votosDemocratas[i]) {

                republicanos += electores[i];

            }
        }

        System.out.println("Los republicanos tienen " + republicanos +
                " electores y los demócratas tienen " + democratas);
    }

    private static void partidoConMasPuntos(int[] votosDemocratas, int[] votosRepublicanos) {

        int democratas = 0;
        int republicanos = 0;

        for (int i = 0; i < votosDemocratas.length; i++) {

            democratas += votosDemocratas[i];

            republicanos += votosRepublicanos[i];
        }

        if (democratas > republicanos) {

            System.out.println("Los demócratas son los más votados con un total de " + democratas);

        } else {

            System.out.println("Los republicanos son los más votados con un total de " + republicanos);
        }
    }

    private static void estadosQueApoyanACadaPartido(int[] votosDemocratas, int[] votosRepublicanos) {

        int estadosDemocratas = 0;
        int estadosRepublicanos = 0;

        for (int i = 0; i < votosDemocratas.length; i++) {

            if (votosDemocratas[i] > votosRepublicanos[i]) {

                estadosDemocratas++;

            } else if (votosRepublicanos[i] > votosDemocratas[i]) {

                estadosRepublicanos++;
            }
        }

        System.out.println("Al partido demócrata lo apoyan " + estadosDemocratas + " estados "
                + " y al republicano " + estadosRepublicanos);
    }

    private static void estadoConMasElectores(int[] votosDemocratas, int[] votosRepublicanos, int[] electores, String[] estados) {

        int electoresDemocratas = 0;
        int electoresRepublicanos = 0;
        int maxDemocratas = 0;
        int maxRepublicanos = 0;
        String estadoDemocrata = "";
        String estadoRepublicano = "";

        for (int i = 0; i < votosDemocratas.length; i++) {

            if (votosDemocratas[i] > votosRepublicanos[i]) {

                electoresDemocratas += electores[i];

                if (electores[i] > maxDemocratas) {

                    maxDemocratas = electores[i];
                    estadoDemocrata = estados[i];
                }

            } else if (votosRepublicanos[i] > votosDemocratas[i]) {

                electoresRepublicanos += electores[i];

                if (electores[i] > maxRepublicanos) {

                    maxRepublicanos = electores[i];
                    estadoRepublicano = estados[i];
                }
            }
        }

        System.out.println("El estado que más electores tiene del partido demócrata es " + estadoDemocrata +
                " tienen un total de " + electoresDemocratas + " y el estado republicano con más electores es " +
                estadoRepublicano + " y tienen un total de " + electoresRepublicanos + " electores");
    }
}