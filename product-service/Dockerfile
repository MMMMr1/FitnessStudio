src
main
test
.gitignore
mvnw
mvnw.cmd
pgadmin.iml
pom.xml
postgresql.iml
FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER MARINA
ADD ./target/product-service-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/product-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080 8089
