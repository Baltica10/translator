FROM openjdk:11-jdk-slim AS final
WORKDIR /data
COPY ./target/translator-001.jar /data/app.jar
ENTRYPOINT ["/bin/sh", "-c", "java -jar -Dserver.port=8085 app.jar"]