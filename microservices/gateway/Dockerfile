FROM openjdk:17-jdk-slim
WORKDIR /receitafacil
COPY target/gateway-0.0.1.jar gateway.jar
EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "gateway.jar"]