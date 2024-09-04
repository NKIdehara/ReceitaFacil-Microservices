FROM openjdk:17-jdk-slim
WORKDIR /receitafacil
COPY target/receita-0.0.1.jar receita.jar
EXPOSE 7920
ENTRYPOINT [ "java", "-jar", "receita.jar"]