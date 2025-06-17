FROM openjdk:17

COPY target/subscription-manager-0.0.1-SNAPSHOT.jar subscription-manager.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "subscription-manager.jar"]