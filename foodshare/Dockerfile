# Imagem base para compilação
FROM maven:3.8.4-openjdk-17-slim AS build

# Copiar os arquivos de configuração
COPY pom.xml /app/
COPY src /app/src/

# Definir o diretório de trabalho
WORKDIR /app

# Compilar a aplicação
RUN mvn clean package -DskipTests

# Imagem base para a execução
FROM openjdk:17-slim

# Copiar o arquivo JAR compilado para a imagem final
COPY --from=build /app/target/foodshare-0.0.1-SNAPSHOT.jar /app/foodshare.jar

# Diretório de trabalho
WORKDIR /app

# Expor a porta do servidor Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["sh", "-c", "java -Dserver.address=${DOCKER_HOST_IP} -jar foodshare.jar"]
