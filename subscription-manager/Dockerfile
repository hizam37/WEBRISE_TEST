FROM openjdk:17-alpine

COPY target/subscription-manager-0.0.1-SNAPSHOT.jar subscription-manager.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "subscription-manager.jar"]