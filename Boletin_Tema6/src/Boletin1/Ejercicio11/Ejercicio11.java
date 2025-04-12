package Boletin1.Ejercicio11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Ejercicio11 {
    public static void main(String[] args) {
        try {
            //crearDirectoriosYFicheros();
            crearDirectoriosYFicherosV2();

        } catch (Ejercicio11Exception | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método crea directorios que son los cursos encontrados en el fichero
     * especificado y dentro de estos se crean los alumnos que pertenezcan a estos
     *
     * @throws Ejercicio11Exception
     */
    public static void crearDirectoriosYFicheros() throws Ejercicio11Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin1/Ejercicio11/Alumnos.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()))) {
                Set<String> infos = new HashSet<>();
                String info;
                while ((info = br.readLine()) != null) {
                    infos.add(info);
                }
                infos.stream().map(s -> {
                    String[] a = s.split(" ");
                    return a[a.length - 1];
                }).filter(s -> {
                    Pattern pattern = Pattern.compile("^[1-4]º[A-Z]{3,}$");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    try {
                        throw new Ejercicio11Exception("Hay un curso que no es válido");

                    } catch (Ejercicio11Exception e) {
                        throw new RuntimeException(e);
                    }
                }).forEach(s -> {
                    try {
                        Path curso = Path.of("Boletin_Tema6/src/Boletin1/Ejercicio11/" + s);
                        if (!Files.exists(curso)) {
                            Files.createDirectory(curso);
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                infos.forEach(s -> {
                    String[] a = s.split(" ");
                    Path alumno = Path.of("Boletin_Tema6/src/Boletin1/Ejercicio11/" + a[a.length - 1] + "/" + a[0]);
                    Pattern pattern = Pattern.compile("^\\p{Lu}\\p{Ll}{2,}\\p{Lu}\\p{Ll}{2,}\\p{Lu}\\p{Ll}{2,}$");
                    Matcher matcher = pattern.matcher(a[0]);
                    if (!matcher.matches()) {
                        try {
                            throw new Ejercicio11Exception("Hay un nombre de alumno inválido");

                        } catch (Ejercicio11Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        if (!Files.exists(alumno)) {
                            Files.createFile(alumno);
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });

            } catch (IOException e) {
                throw new Ejercicio11Exception(e.getMessage());
            }

        } catch (InvalidPathException e) {
            throw new Ejercicio11Exception(e.getMessage());
        }
    }

    /**
     * Este método crea directorios que son los cursos encontrados en el fichero
     * especificado y dentro de estos se crean los alumnos que pertenezcan a estos
     * cursos, esta es una segunda versión del método anterior, aquí lo hago con
     * un mapa
     *
     * @throws Ejercicio11Exception
     */
    public static void crearDirectoriosYFicherosV2() throws Ejercicio11Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin1/Ejercicio11/Alumnos.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()))) {
                Set<String> infos = new HashSet<>();
                String info;
                while ((info = br.readLine()) != null) {
                    infos.add(info);
                }
                Map<String, List<String>> cursos = infos.stream().collect(Collectors.groupingBy(s -> s.split(" ")
                        [s.split(" ").length - 1], Collectors.mapping(s -> s.split(" ")[0],
                        Collectors.toList())));

                cursos.entrySet().forEach(m -> {
                    Pattern pattern = Pattern.compile("^[1-4]º[A-Z]{3,}$");
                    Matcher matcher = pattern.matcher(m.getKey());
                    Path directorio = Path.of("Boletin_Tema6/src/Boletin1/Ejercicio11/" + m.getKey());
                    if (!matcher.matches()) {
                        try {
                            throw new Ejercicio11Exception("El nombre del fichero no es correcto");

                        } catch (Ejercicio11Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (!Files.exists(directorio)) {
                        try {
                            Files.createDirectory(directorio);
                            m.getValue().forEach(v -> {
                                Pattern pattern1 = Pattern.compile("^\\p{Lu}\\p{Ll}{2,}\\p{Lu}\\p{Ll}{2,}\\p{Lu}\\p{Ll}{2,}$");
                                Matcher matcher1 = pattern1.matcher(v);
                                Path fichero = Path.of("Boletin_Tema6/src/Boletin1/Ejercicio11/" + m.getKey() + "/" + v);
                                if (!matcher1.matches()) {
                                    try {
                                        throw new Ejercicio11Exception("Hay un nombre de alumno no válido");

                                    } catch (Ejercicio11Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                if (!Files.exists(fichero)) {
                                    try {
                                        Files.createFile(fichero);

                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            } catch (IOException e) {
                throw new Ejercicio11Exception(e.getMessage());
            }

        } catch (InvalidPathException e) {
            throw new Ejercicio11Exception(e.getMessage());
        }
    }
}