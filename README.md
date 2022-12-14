## Acerca de
La tarea consiste en realizar un aplicativo de bibloteca que permita crear libros, agregarlos a una biblioteca y luego realizar préstamos de los mismos.

## Test a implementar

|ítem	| Detalle| 
|---- | ---- |
|TEST SearchExistingBookyTitle| Buscar un libro existente en la biblioteca|
|TEST SearchNonExistingBookyTitle| Buscar un libro no existente en la biblioteca|
|TEST SearchExistingBookyAuthor| Buscar un libro existente en la biblioteca por autor|
|TEST SearchNonExistingBookyAuthor| Buscar un libro no existente en la biblioteca por autor|
|TEST SearchExistingBookyISBN| Buscar un libro existente en la biblioteca por ISBN|
|TEST SearchNonExistingBookyISBN| Buscar un libro no existente en la biblioteca por ISBN|
|TEST testAddNotExistingBook| Agregar un libro a la biblioteca|
|TEST testAddExistingBook| Agregar un libro existente a la biblioteca|
|TEST testChageBookStatusToAvailable | Cambiar el estado de un libro a disponible|
|TEST testChageBookStatusToNotAvailable | Cambiar el estado de un libro a no disponible|
|TEST TestChangeUnexistingBookStatus | Cambiar el estado de un libro no existente|
|Test TestEditExistingBook | Editar un libro existente|
|Test TestEditNonExistingBook | Editar un libro no existente|
|Test TestRemoveExistingBook | Eliminar un libro existente|
|Test TestRemoveNonExistingBook | Eliminar un libro no existente|

## Obtener las fuentes

Para obtener las fuentes y sus dependencias se deben realizar los siguientes pasos:

- Clonar el repositorio

`git clone https://github.com/xavierutox/Junit-library-project`

- Obtener gradle y las dependencias

Ejecutar el binario `gradlew` o `gradlew.bat` según el sistema operativo utilizado.

`./gradlew`

## Ejecutar las Pruebas

Una vez obtenidas las dependencias podemos ejecutar `./gradlew test`

## Compilar la aplicación

Una vez realizadas las pruebas compilamos nuestro ejecutable: `./gradlew shadowJar`

## Ejecutar la aplicación

El resultado de la compilación se puede ejecutar a través de: 

`java -jar build/libs/Library-1.0-SNAPSHOT-all.jar`
