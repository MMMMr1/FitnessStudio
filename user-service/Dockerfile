FROM openjdk:11
MAINTAINER MARINA
ADD ./target/user-service-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/user-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080