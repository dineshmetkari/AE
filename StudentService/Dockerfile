FROM openjdk:8
ADD target/UserResultService-0.0.1-SNAPSHOT.jar useresultdocker.jar
EXPOSE 9999
RUN bash -c 'touch /useresultdocker.jar'
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/users","-Djava.security.egd=file:/dev/./urandom","-jar","/useresultdocker.jar"]
