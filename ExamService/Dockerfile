FROM openjdk:8
ADD target/ExamCreation-0.0.1-SNAPSHOT.jar examdocker.jar
EXPOSE 8074
RUN bash -c "touch /examdocker.jar"
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/users","-Djava.security.egd=file:/dev/./urandom","-jar","examdocker.jar"]