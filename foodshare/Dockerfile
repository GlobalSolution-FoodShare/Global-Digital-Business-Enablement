FROM maven:3.8.4-openjdk-17-slim AS build

COPY pom.xml /app/
COPY src /app/src/

WORKDIR /app

RUN mvn clean package -DskipTests

FROM openjdk:17-slim

COPY --from=build /app/target/foodshare-0.0.1-SNAPSHOT.jar /app/foodshare.jar

WORKDIR /app

EXPOSE 8080

CMD ["sh", "-c", "java -Dserver.address=${DOCKER_HOST_IP} -jar foodshare.jar"]
