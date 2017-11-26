FROM openjdk:8
ADD target/EvaluatorService-0.0.1-SNAPSHOT.jar mcqevaluatordocker.jar
EXPOSE 8022
RUN bash -c 'touch /mcqevaluatordocker.jar'
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/users","-Djava.security.egd=file:/dev/./urandom","-jar","/mcqevaluatordocker.jar"]
 
