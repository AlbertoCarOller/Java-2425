public class CancionNavidad {
    public static void main(String[] args) {

        String estribillo = " Feliz Navidad, Feliz Navidad\n";
        StringBuilder cancion = new StringBuilder();
        cancion.append("El módulo de programación quiero aprobar.")
                .append("A mi profesor favorito tendré que sobornar.")
                .append("Jamón y gambas tendré que comprar.")
                .append("Porque si no me tocará pringar.")
                .append("Y el año que viene el doble tendré que pagar.");

        String cancionTerminada = hacerCancion(estribillo, cancion);
        System.out.println(cancionTerminada);
    }

    public static String hacerCancion(String estribillo, StringBuilder cancion) {

        String cancionString = cancion.toString();
        String[] partes = cancionString.split("\\.");
        StringBuilder cancionCompleta = new StringBuilder();

        for (int i = 0; i < partes.length; i++) {

            cancionCompleta.append(partes[i]).append(".").append(estribillo);
        }
        return cancionCompleta.toString();
    }
}