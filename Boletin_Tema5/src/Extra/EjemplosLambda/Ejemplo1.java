package Extra.EjemplosLambda;

import java.util.Arrays;
import java.util.List;

public class Ejemplo1 {
    public static void main(String[] args) {
        List<String> nombres = Arrays.asList("Ana", "Luis", "Pedro", "Luis");

        //nombres.stream().filter(n -> n.length() > 3).forEach(System.out::println);
        //nombres.stream().filter(n -> n.startsWith("A")).forEach(System.out::println);
        //nombres.stream().map(String::length).forEach(System.out::println);
        //nombres.stream().sorted((n1, n2) -> n2.length() - n1.length()).forEach(System.out::println);
        //List<String> lista = nombres.stream().toList();
    }
}