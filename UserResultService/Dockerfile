FROM openjdk:8
ADD target/UserResultService-0.0.1-SNAPSHOT.jar userresultdocker.jar
EXPOSE 9994
RUN bash -c 'touch /userresultdocker.jar'
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/users","-Djava.security.egd=file:/dev/./urandom","-jar","userresultdocker.jar"]
