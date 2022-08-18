FROM amazoncorretto:11-alpine-jdk
COPY target/simple-rest-service-1.0.0-SNAPSHOT.war simple-rest-service-1.0.0-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/simple-rest-service-1.0.0-SNAPSHOT.war"]