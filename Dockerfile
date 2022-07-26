FROM amazoncorretto:11-alpine-jdk
COPY target/simple-rest-service-0.0.1-SNAPSHOT.war simple-rest-service-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/simple-rest-service-0.0.1-SNAPSHOT.war"]