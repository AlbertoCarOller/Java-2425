package Fichero;

public class FicheroApp {
    public static void main(String[] args) {

        FicheroEjecutable ft = new FicheroEjecutable("ficheroE1", crearArrays(), 775);
        FicheroVideo fv = new FicheroVideo("ficheroV1", crearArrays(), 10);
        FicheroAudio fa = new FicheroAudio("ficheroA1", crearArrays(), 20);
        FicheroImagen fm = new FicheroImagen("ficheroI1", crearArrays(), Formato.JPG);
        String[] p1 = new String[]{"shdjsjdjsd", "hjhjjjhj", "iyuggvh"};
        FicheroTextoPlano ftp = new FicheroTextoPlano("ficheroTP1", p1);
        String[] p2 = new String[]{"jfif", "jfdfdfj", "jkdsjfkdsj"};
        FicheroTextoFormateado ftf = new FicheroTextoFormateado("ficheroTF1", p2, Fuente.ROBOTO, 30, "verde");

        Fichero[] ficheros = new Fichero[]{ft, fv, fa, fm, ftp, ftf};

        for (Fichero fichero : ficheros) {

            if (fichero instanceof Analizable) {
                Analizable a = (Analizable) fichero;
                a.analizar();
            }

            if (fichero instanceof Leible) {
                Leible l = (Leible) fichero;
                System.out.println("Leyendo " + l.leer());
            }

            if (fichero instanceof Representable) {
                Representable r = (Representable) fichero;
                r.mostrar();
            }

            if (fichero instanceof Reproducible reproducible) {
                reproducible.reproducir();
            }
        }
    }

    public static Byte[] crearArrays() {
        int tamanoArray = (int) (Math.random() * 999) + 1;
        return new Byte[tamanoArray];
    }
}
