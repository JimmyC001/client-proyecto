# COMPILE
FROM maven:3.8.4-openjdk-17-slim as builder
RUN apt-get update 
WORKDIR /app
COPY src src
COPY pom.xml .
RUN mvn clean package
# EXEC
FROM openjdk:17-jdk-slim
RUN apt-get update
RUN apt-get install -y netcat
WORKDIR /app
COPY --from=builder /app/target/app.jar .
COPY start.sh .
RUN chmod +x ./start.sh
ENTRYPOINT ["./start.sh"]