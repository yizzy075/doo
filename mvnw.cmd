@echo off
REM Apache Maven Wrapper (mvnw.cmd) - minimal standard script
SET MAVEN_PROJECTBASEDIR=%~dp0
SET MAVEN_WRAPPER_DIR=%MAVEN_PROJECTBASEDIR%.mvn\wrapper

java -cp "%MAVEN_WRAPPER_DIR%\maven-wrapper.jar" org.apache.maven.wrapper.MavenWrapperMain %*

