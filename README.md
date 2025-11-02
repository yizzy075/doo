Estado del proyecto Maven

Qué hice:
- Creé un `pom.xml` raíz en la carpeta del proyecto (`doo/pom.xml`) con packaging `pom` y que agrega el módulo `ApiNose`.
- No modifiqué los `pom.xml` existentes dentro de `ApiNose`.

Resultado de la verificación y build:
- Al intentar usar el wrapper incluido (`ApiNose\mvnw.cmd`) la ejecución falló porque faltan los archivos del Maven Wrapper en `ApiNose\.mvn\wrapper` (en particular `maven-wrapper.properties` y `maven-wrapper.jar`).
- En el sistema no se encontró un ejecutable `mvn` (Maven no está instalado o no está en PATH), por eso no pude construir el proyecto aquí.

Siguientes pasos recomendados (elige uno):

Opción A — Instalar Maven en Windows (recomendado si quieres usar Maven globalmente):
1. Instalar Apache Maven: https://maven.apache.org/download.cgi
2. Extraer y añadir la carpeta `bin` de Maven al PATH del sistema.
3. Desde la raíz del repo (carpeta `doo`) ejecutar en cmd.exe:
   mvn -v
   mvn -DskipTests package

Opción B — Generar el Maven Wrapper localmente (útil si no quieres instalar Maven globalmente):
1. Si tienes Maven instalado localmente, desde la raíz del repo ejecutar:
   mvn -N io.takari:maven:wrapper
   Esto generará los archivos `.mvn/wrapper/maven-wrapper.jar` y los scripts `mvnw`/`mvnw.cmd`.
2. Agrega y commitea los archivos generados al repositorio.
3. Entonces podrás correr (Windows cmd.exe):
   mvnw.cmd -DskipTests package

Opción C — Si quieres que yo añada el wrapper al repo por ti:
- Puedo crear los scripts (`mvnw`, `mvnw.cmd`) y el archivo `.mvn/wrapper/maven-wrapper.properties`, pero no puedo generar ni añadir `maven-wrapper.jar` desde aquí sin descargar un binario (requiere acceso a la red o que me suministres el jar). Si prefieres esto, dime y lo preparo; luego deberás proporcionar el `maven-wrapper.jar` o permitirme descargarlo.

Notas adicionales:
- El `pom.xml` raíz creado tiene Java 21 como versión objetivo (coincide con los poms existentes). Si usas una JDK diferente, actualiza `<maven.compiler.source>` y `<maven.compiler.target>` o instala JDK 21.
- Si quieres que agregue otros módulos del repo al aggregator, indícalo (por ejemplo hay otras carpetas `src` sueltas; puedo analizarlas y convertirlas en módulos si procede).

Estado de requisitos:
- Crear `pom.xml` raíz: Done
- Validar POMs estáticos: Done (no errores de sintaxis)
- Construcción automática: Deferred (necesita Maven o el wrapper funcional)

Si quieres, procedo con:
- A) Añadir el Maven Wrapper completo al repo (necesitaré permiso para descargar el jar o que me lo des). 
- B) Ayudarte a instalar Maven en Windows con instrucciones paso a paso y verificar la construcción aquí.
- C) Convertir otras carpetas en módulos Maven y ajustar el aggregator.

Dime cuál opción prefieres y lo hago. (Si eliges A y permites descarga, lo haré automáticamente; si eliges B te daré comandos exactos para cmd.exe).
