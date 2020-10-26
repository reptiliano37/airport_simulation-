# airport_simulation-

Proyecto en Java para Paradigmas de Programación:

- Focalizado en **programación concurrente y comunicación cliente-servidor.

En la siguiente clase se instancian todas las clases que van a ser usadas, para que el funcionamiento y la visualización de mi aplicación sea correcta. El orden del programa es el siguiente:

[Main.java](/blob/main/Aeropuerto/src/main/java/com/mycompany/aeropuerto/Main.java)

[Carpeta con clases principales](/Aeropuerto/src/main/java/com/mycompany/aeropuerto/)

1. Instanciación de las clases con los parámetros adecuados acorde al enunciado.
2. Comienzan mediante el método start() las clases hilo, primero Pasajero, y a continuación Empleado.
3. Los pasajeros (cada uno de ellos es un thread) van depositando las maletas en la Cinta (primer buffer). En total tienen que depositar 80. La cinta se llena cada vez que hay 8 maletas.
4. Los empleados (2 hilos) van extrayendo las maletas de una en una tardando el mismo tiempo en llevar cada una de ellas al Avión (segundo buffer) y en volver a por otra. Tenemos la opción de pausar todo el proceso, pausar el empleado 1 y/o pausar el empleado 2.
5. Todas las acciones que realizan los Pasajeros y los Empleados se recogen en el archivo evolucionAeropuerto.txt con la hora y fecha en el momento que se ha realizado. Esto es posible gracias a mi clase Log.

En la segunda parte de mi programa, he implementado el módulo capaz de visualizar de forma remota el contenido de la cinta y el contenido de la bodega del avión, utilizando programación concurrente distribuida. Para ello ha sido necesaria el desarrollo de 3 clases nuevas y utilizar mis clases Cinta y Avion como “servidores”:

- InterfaceCliente
- Cliente
- InterfaceServidor
- Cinta
- Avion

Decidí implementar dicho módulo como una aplicación **Cliente – Servidor RMI** (invocación de métodos remotos en Java). Desde mi punto vista, me parece mucho más sencillo y actualizado acorde al desarrollo de aplicaciones web la implementación de este método, en vez de sockets, así como la rapidez.

