FROM openjdk:11
MAINTAINER MARINA
ADD ./target/audit-service-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/audit-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080 8089