FROM openjdk:17-jdk-slim
WORKDIR /receitafacil
COPY target/medida-0.0.1.jar medida.jar
EXPOSE 7950
ENTRYPOINT [ "java", "-jar", "medida.jar"]