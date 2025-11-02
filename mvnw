#!/usr/bin/env sh
# Apache Maven Wrapper (mvnw) - minimal standard script
# ...existing code...
MAVEN_PROJECTBASEDIR="$(cd "$(dirname "$0")" && pwd)"
__MAVEN_WRAPPER_DIR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper"

java -cp "$__MAVEN_WRAPPER_DIR/maven-wrapper.jar" org.apache.maven.wrapper.MavenWrapperMain "$@"

