FROM openjdk:8
ADD target/eurekaServer-0.0.1-SNAPSHOT.jar eurekaServerDocker.jar
EXPOSE 8070
RUN bash -c 'touch /eurekaServerDocker.jar'
ENTRYPOINT ["java", "-jar", "eurekaServerDocker.jar"]