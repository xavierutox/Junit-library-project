## Acerca de
Este proyecto es un fork del ejemplo https://github.com/Pruebas-de-Software/JUnit-Retiros-y-Depositos bajo la licencia GNU GENERAL PUBLIC LICENSE, Version 3, 29 June 2007.
Gran parte del readme es de su autoría al igual que la estructura base del proyecto.

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

- Ejecutar gradlew

 `./gradlew`

## Ejecutar las Pruebas

Para ejecutar las pruebas en Junit5 se debe escribir:  `./gradlew test`

Se generara un test de cobertura en `build/customJacocoReportDir/test/html/index.html`

## Compilar la aplicación

Se puede generar un ejecutable con: 
 `./gradlew shadowJar`

## Ejecutar la aplicación

El ejecutable se puede ejecutar con:

`java -jar build/libs/Library-1.0-SNAPSHOT-all.jar`
