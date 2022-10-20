## POC DE PRISA - DIARIO AS

[![selenium - poc de prisa](https://github.com/hiberus-prisa-qa-devops/POC_Selenium/actions/workflows/selenium.yml/badge.svg)](https://github.com/hiberus-prisa-qa-devops/POC_Selenium/actions/workflows/selenium.yml)

## Resultado de la última ejecución

Enlace para acceder a los reportes que ha generado Allure. Por defecto, se visualiza el reporte de la ultima ejecución lanzada. Para poder comprobar las ejecuciones anteriores pulsar sobre cualquier pico del gráfico que aparece en el lado derecho del reporte. Conf: Guardar los últimos 10 reportes.

https://hiberus-prisa-qa-devops.github.io/POC_Selenium/

## Descripción

Este repositorio contiene un proyecto Selenium para la prueba de concepto de Prisa. Las pruebas se lanzan sobre el site en producción de Diario AS.
¿Qué es Selenium? Selenium es una herramienta open-source de control de calidad para automatizar y ejecutar pruebas funcionales y de regresión sobre una aplicación. Para más información sobre la herramienta consultar la documentación de confluence.  

## Workflow

El workflow corre sobre un sistema operativo `macos-latest` y la batería de pruebas pueden lanzarse tanto en `Google Chrome` como en `Apple Safari`. Una vez finalizado se generan los diferentes reportes:

- ExtentReports
  - HTML: Genera un archivo .html ligero y fácil de usar y lo almacena en un artefacto
  - PDF: Genera un complejo archivo .pdf y lo almacena en un artefacto 
- Monte
  - Video: Genera un archivo .avi de cada caso y los almacena todos en un artefacto
- Allure
  - HTML: Genera un reporte web de alto nivel y trazabilidad y lo almacena en un artefacto. Se necesita instalar allure en local para poder abrirlo
 
Una vez finalizado el workflow, Github lanza de forma automatiza un bot que despliega el artefacto de Allure en una página (enlace del primer bloque del readme).

Para más información del workflow revisar el archivo localizado en la siguiente ruta del proyecto:

```
.github/workflows/selenium.yml
```

## Ruta de los artefactos

Para poder comprobar los artefactos generados durante el workflow hay que acceder a la propia ejecución. Para ello podemos seguir los siguientes pasos:

1. Acceder a la pestaña de `Actions`
2. En el menú de la izquierda seleccionar el workflow `selenium - poc de prisa`
3. Seleccionar la ejecución deseada
4. Bajar hasta el fondo de la página al apartado `Artifacts`
5. Pulsar sobre el artefacto deseado 
6. Una vez descargado, descomprimir el archivo .zip

## Casos de prueba / escenarios

El proyecto consiste de cuatro casos de prueba decididos por el QA Lead y el POC.

- Access to motor league news
  - AC: The Atletico de Madrid news are displayed
- Return to home from a football page
  - AC: The user is redirected to the home page
- Share news with Facebook
  - AC: The Facebook share window is displayed
- Access to motor league news
  - AC: The Formula One league advertisement element is displayed

Para más información de los casos de prueba y los steps que los forman revisar el archivo feature localizado en la siguiente ruta del proyecto:

```
src/test/resources/functionalTest/diarioAS.feature
```

## Tecnologías utilizadas

- Herramienta: Selenium WebDriver
- IDE: IntelIJ
- Compilación: Maven
- Lenguaje: Java
- Framework: JUnit 4
- BDD: Cucumber 7
- Reporte: Allure & ExtentReports

## Requisitos para lanzar en local

En caso de que se quiera probar ejecutar el proyecto en local se necesita cumplir los siguientes requisitos:

- Tener un equipo con al menos 4 GB de RAM
- Disponer del navegador que se vaya a escoger a la hora de lanzar el proyecto
- Conexión decente para que los pasos cuenten con el tiempo necesario
- Java JDK 11 con la ruta home configurada
- Maven con la ruta home configurada
- IntelIj para poder abrir el proyecto y lanzarlo

## Comando para ejecutar Selenium

- Sin especificar navegador (Chrome por defecto)

```sh
mvn clean test
```

- Especificando navegador (Chrome, Safari)

```sh
mvn clean test -Dbrowser=<navegador>
```