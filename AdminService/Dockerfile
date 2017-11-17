FROM openjdk:8
ADD target/Admin_Demo.jar adminDocker.jar
EXPOSE 8089
RUN bash -c 'touch /studentDocker.jar'
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/users","-Djava.security.egd=file:/dev/./urandom","-jar","/studentDocker.jar"]
