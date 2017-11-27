FROM openjdk:8
ADD target/Student_demo-0.0.1-SNAPSHOT.jar studentDocker.jar
EXPOSE 8083
RUN bash -c 'touch /studentDocker.jar'
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/users","-Djava.security.egd=file:/dev/./urandom","-jar","/studentDocker.jar"]
 
