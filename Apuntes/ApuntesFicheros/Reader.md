__¿Qué es Reader?__
Es una clase abstracta que permite que permite leer la entrada de caracteres únicamente, __no permite bytes__. Esta __es la clase base de varias clases__ que facilitan la lectura de diferentes archivos.

__Clases derivadas:__

| Clases            | Información                                                                                              |
| ----------------- | -------------------------------------------------------------------------------------------------------- |
| [[FileReader]]    | Permite leer caracteres desde un archivo                                                                 |
| [[InputStreamReader]] | Convierte un flujo de bytes (InputStream) en un flujo de caracteres (Reader)                             |
| [[BufferedReader]]    | Ofrece una versión bufferizada de un Reader, mejorando la eficiencia al leer grandes cantidades de texto |

