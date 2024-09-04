FROM openjdk:17-jdk-slim
WORKDIR /receitafacil
COPY target/publicacao-0.0.1.jar publicacao.jar
EXPOSE 7999
ENTRYPOINT [ "java", "-jar", "publicacao.jar"]