## Acerca de
La tarea consiste en construir un aplicativo del "Blue Bank" que permita realizar 2 tipos de transacciones electrónicas:

- Retirar dinero desde cuenta
- Depositar dinero en cuenta

Se debe tener en consideración que un usuario maneja dos tipos de cuentas (En 'CLP' y en 'USD').

## Test a implementar

|ítem	| Detalle| 
|---- | ---- |
|Test Inicialización|	|
|Test de Sesión	||
|Test Número de Operaciones por Sesión, en rango|	|
|Test Número de Operaciones por Sesión, valor límite||	
|Test Número de Sesiones, en rango	||
|Test Número de Sesiones, valor límite	||
|Test de Reinicio de Sesión	||
|Test depósito||	
|Test CantidadNoPermitida en CLP||	
|Test CantidadNoPermitida en USD||	
|Test CantidadNegativa en CLP	||
|Test CantidadNegativa en USD	||
|Test DespositoMontoCorrecto CLP||	
|Test DepositoMontoCorrecto USD||	
|Test DepositosIncrementos CLP|	Varias operaciones en test|
|Test DepositoIncrements USD|	Varias operaciones en test|
|Test DepositoMinimoPermitido CLP||	
|Test DepositoMinimoPermitidoUSD||	
|Test Retiro	||
|Test CantidadNoPermitida en CLP||	
|Test CantidadNoPermitida en USD	||
|Test CantidadNegativa en CLP	||
|Test CantidadNegativa en USD	||
|Test RetiroMontoCorrecto CLP	||
|Test RetiroMontoCorrecto USD	||
|Test RetiroDremento en CLP|	Varias operaciones en test|
|Test RetiroDremento USD|	Varias operaciones en test|
|Test RetiroMinimoPermitido CLP|	|
|Test RetiroMinimoPermitido USD	||
|Test RetiroMáximoPermitido CLP	||
|Test RetiroMáximoPermitido USD	||
|Test RetiroVaciadoDeCuenta USD	||
|Test RetiroVaciadoDeCuenta CLP	||
|Test Historico de Transacciones	||
|Test HistoricoTransaccionesInicio	||
|Test HistoricoTransaccionesDeposito  CLP||	
|Test HistoricoTransaccionesDeposito USD	||
|Test HistoricoTransaccioneRetiro CLP	||
|Test HistorioTransaccionesRetiro USD	||
|TestAplicativo|	|
|TestSet	|En caso de ser necesarios|
|TestGetters|	En caso de ser necesarios||


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
