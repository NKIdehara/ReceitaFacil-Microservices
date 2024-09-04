FROM openjdk:17-jdk-slim
WORKDIR /receitafacil
COPY target/ingrediente-0.0.1.jar ingrediente.jar
EXPOSE 7930
ENTRYPOINT [ "java", "-jar", "ingrediente.jar"]