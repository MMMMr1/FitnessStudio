FROM openjdk:11
MAINTAINER MARINA
ADD ./target/eureka-server-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/eureka-server-0.0.1-SNAPSHOT.jar"]

EXPOSE 8761