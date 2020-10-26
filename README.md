# airport_simulation-

Proyecto en Java para Paradigmas de Programación:

- Focalizado en programación concurrente y comunicación cliente-servidor

En la clase [Main.java] se instancian todas las clases que van a ser usadas, para que el funcionamiento y la visualización de mi aplicación sea correcta. El orden del programa es el siguiente:

1. Instanciación de las clases con los parámetros adecuados acorde al enunciado.
2. Comienzan mediante el método start() las clases hilo, primero Pasajero, y a continuación Empleado.
3. Los pasajeros (cada uno de ellos es un thread) van depositando las maletas en la Cinta (primer buffer). En total tienen que depositar 80. La cinta se llena cada vez que hay 8 maletas.
4. Los empleados (2 hilos) van extrayendo las maletas de una en una tardando el mismo tiempo en llevar cada una de ellas al Avión (segundo buffer) y en volver a por otra. Tenemos la opción de pausar todo el proceso, pausar el empleado 1 y/o pausar el empleado 2.
5. Todas las acciones que realizan los Pasajeros y los Empleados se recogen en el archivo evolucionAeropuerto.txt con la hora y fecha en el momento que se ha realizado. Esto es posible gracias a mi clase Log.
