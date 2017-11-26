FROM openjdk:8
ADD target/engineService-0.0.1-SNAPSHOT.jar enginedocker.jar
EXPOSE 9998
RUN bash -c 'touch /enginedocker.jar'
ENTRYPOINT ["java","-jar","/enginedocker.jar"]