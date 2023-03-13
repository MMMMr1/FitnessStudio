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
ADD ./target/fitness-service-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/fitness-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080 8089

#COPY target/fitness-service-0.0.1-SNAPSHOT.jar fitness-service.jar
#ENTRYPOINT ["java","-jar","/fitness-service.jar"]
#ADD ./target/fitness-service-0.0.1-SNAPSHOT.jar /fitness-service/

#CMD ["java", "-Xmx200m", "-jar", "/fitness-service/fitness-service.jar"]
#
EXPOSE 8080 8089
## For Java 11, try this
#FROM adoptopenjdk/openjdk11:alpine-jre
#
## Refer to Maven build -> finalName
#ARG JAR_FILE=target/fitness-service-0.0.1-SNAPSHOT.jar
#
## cd /opt/app
#WORKDIR /opt/app
#
## cp target/spring-boot-web.jar /opt/app/app.jar
#COPY ${JAR_FILE} app.jar
#
## java -jar /opt/app/app.jar
#ENTRYPOINT ["java","-jar","app.jar"]