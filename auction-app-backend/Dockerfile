FROM maven:3.9.6-eclipse-temurin-17-alpine as build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:resolve

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy

COPY --from=build /app/target/*.jar AuctionApp.jar

CMD ["java","-jar","AuctionApp.jar"]
