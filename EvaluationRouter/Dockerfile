FROM openjdk:8
ADD target/evaluationrouter-0.0.1-SNAPSHOT.jar evalrouterdocker.jar
EXPOSE 9996
RUN bash -c "touch /evalrouterdocker.jar"
ENTRYPOINT ["java","-jar","evalrouterdocker.jar"]