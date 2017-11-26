FROM openjdk:8
ADD target/evaluationmanager-0.0.1-SNAPSHOT.jar evaluationdocker.jar
EXPOSE 9997
RUN bash -c 'touch /evaluationdocker.jar'
ENTRYPOINT ["java","-jar","/evaluationdocker.jar"]
