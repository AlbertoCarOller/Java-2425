public class Ejercicio1 {

    public static void main(String[] args) {
        int num = makeNegative(-3);
        System.out.println(num);
    }

    public static int makeNegative(final int x) {
        int y;

        if (x > 0) {
            y = x * -1;

        } else {
            y = x;
        }
        return y;
    }
}