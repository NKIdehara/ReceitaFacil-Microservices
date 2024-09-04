FROM openjdk:17-jdk-slim
WORKDIR /receitafacil
COPY target/usuario-0.0.1.jar usuario.jar
EXPOSE 7910
ENTRYPOINT [ "java", "-jar", "usuario.jar"]