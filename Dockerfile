FROM openjdk:latest
COPY target/SpaceApplication-1.0-SNAPSHOT.jar SpaceApplication-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/SpaceApplication-1.0-SNAPSHOT.jar"]

