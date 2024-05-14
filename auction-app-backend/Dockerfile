FROM maven:3.8.5-openjdk-17 as build
COPY . .
RUN ls -la
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/auction-app-0.0.1-SNAPSHOT.jar auction-app.jar
RUN ls -la
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "auction-app.jar"]
