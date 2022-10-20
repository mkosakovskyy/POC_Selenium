1. [ ] # POC DE PRISA - DIARIO AS
2. [ ] 
3. [ ] [![selenium - poc de prisa](https://github.com/hiberus-prisa-qa-devops/POC_Selenium/actions/workflows/selenium.yml/badge.svg)](https://github.com/hiberus-prisa-qa-devops/POC_Selenium/actions/workflows/selenium.yml)
4. [ ] 
5. [ ] ## Resultado de la última ejecución
6. [ ] 
7. [ ] Enlace para acceder a los reportes que ha generado Allure. Por defecto, se visualiza el reporte de la ultima ejecución lanzada. Para poder comprobar las ejecuciones anteriores pulsar sobre cualquier pico del gráfico que aparece en el lado derecho del reporte. Conf: Guardar los últimos 10 reportes.
8. [ ] 
9. [ ] https://hiberus-prisa-qa-devops.github.io/POC_Selenium/
10. [ ] 
11. [ ] ## Descripción
12. [ ] 
13. [ ] Este repositorio contiene un proyecto Selenium para la prueba de concepto de Prisa. Las pruebas se lanzan sobre el site en producción de Diario AS.
14. [ ] ¿Qué es Selenium? Selenium es una herramienta open-source de control de calidad para automatizar y ejecutar pruebas funcionales y de regresión sobre una aplicación. Para más información sobre la herramienta consultar la documentación de confluence.  
15. [ ] 
16. [ ] ## Workflow
17. [ ] 
18. [ ] El workflow corre sobre un sistema operativo `macos-latest` y la batería de pruebas pueden lanzarse tanto en `Google Chrome` como en `Apple Safari`. Una vez finalizado se generan los diferentes reportes:
19. [ ] 
20. [ ] - ExtentReports
21. [ ]   - HTML: Genera un archivo .html ligero y fácil de usar y lo almacena en un artefacto
22. [ ]   - PDF: Genera un complejo archivo .pdf y lo almacena en un artefacto 
23. [ ] - Monte
24. [ ]   - Video: Genera un archivo .avi de cada caso y los almacena todos en un artefacto
25. [ ] - Allure
26. [ ]   - HTML: Genera un reporte web de alto nivel y trazabilidad y lo almacena en un artefacto. Se necesita instalar allure en local para poder abrirlo
27. [ ]   
28. [ ] Una vez finalizado el workflow, Github lanza de forma automatiza un bot que despliega el artefacto de Allure en una página (enlace del primer bloque del readme).
29. [ ] 
30. [ ] Para más información del workflow revisar el archivo localizado en la siguiente ruta del proyecto:
31. [ ] ```
32. [ ] .github/workflows/selenium.yml
33. [ ] ```
34. [ ] 
35. [ ] ## Ruta de los artefactos
36. [ ] 
37. [ ] Para poder comprobar los artefactos generados durante el workflow hay que acceder a la propia ejecución. Para ello podemos seguir los siguientes pasos:
38. [ ] 
39. [ ] 1. Acceder a la pestaña de `Actions`
40. [ ] 2. En el menú de la izquierda seleccionar el workflow `selenium - poc de prisa`
41. [ ] 3. Seleccionar la ejecución deseada
42. [ ] 4. Bajar hasta el fondo de la página al apartado `Artifacts`
43. [ ] 5. Pulsar sobre el artefacto deseado
44. [ ] 6. Una vez descargado, descomprimir el archivo .zip
45. [ ] 
46. [ ] ## Casos de prueba / escenarios
47. [ ] 
48. [ ] El proyecto consiste de cuatro casos de prueba decididos por el QA Lead y el POC.
49. [ ] 
50. [ ] - Access to motor league news
51. [ ]   - AC: The Atletico de Madrid news are displayed
52. [ ] - Return to home from a football page
53. [ ]   - AC: The user is redirected to the home page
54. [ ] - Share news with Facebook
55. [ ]   - AC: The Facebook share window is displayed
56. [ ] - Access to motor league news
57. [ ]   - AC: The Formula One league advertisement element is displayed
58. [ ] 
59. [ ] Para más información de los casos de prueba y los steps que los forman revisar el archivo feature localizado en la siguiente ruta del proyecto:
60. [ ] ```
61. [ ] src/test/resources/functionalTest/diarioAS.feature
62. [ ] ```
63. [ ] 
64. [ ] ## Tecnologías utilizadas
65. [ ] 
66. [ ] - Herramienta: Selenium WebDriver
67. [ ] - IDE: IntelIJ
68. [ ] - Compilación: Maven
69. [ ] - Lenguaje: Java
70. [ ] - Framework: JUnit 4
71. [ ] - BDD: Cucumber 7
72. [ ] - Reporte: Allure & ExtentReports
73. [ ] 
74. [ ] ## Requisitos para lanzar en local
75. [ ] 
76. [ ] En caso de que se quiera probar ejecutar el proyecto en local se necesita cumplir los siguientes requisitos:
77. [ ] - Tener un equipo con al menos 4 GB de RAM
78. [ ] - Disponer del navegador que se vaya a escoger a la hora de lanzar el proyecto
79. [ ] - Conexión decente para que los pasos cuenten con el tiempo necesario
80. [ ] - Java JDK 11 con la ruta home configurada
81. [ ] - Maven con la ruta home configurada
82. [ ] - IntelIj para poder abrir el proyecto y lanzarlo
83. [ ] 
84. [ ] ## Comando para ejecutar Selenium
85. [ ] 
86. [ ] - Sin especificar navegador (Chrome por defecto)
87. [ ] 
88. [ ] ```sh
89. [ ] mvn clean test
90. [ ] ```
91. [ ] 
92. [ ] - Especificando navegador (Chrome, Safari)
93. [ ] 
94. [ ] ```sh
95. [ ] mvn clean test -Dbrowser=<navegador>
96. [ ] ```
97. [ ] 