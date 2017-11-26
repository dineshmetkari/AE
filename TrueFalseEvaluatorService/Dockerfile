FROM openjdk:8
ADD target/
EXPOSE 8023
RUN bash -c 'touch /studentDocker.jar'
ENTRYPOINT ["java","-jar","/studentDocker.jar"]
