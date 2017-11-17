FROM openjdk:8
ADD target/authenticationservice-1.0.jar authenticationServiceDocked.jar
EXPOSE 8088
RUN bash -c 'touch /authenticationServiceDocked.jar'
ENTRYPOINT ["java","-jar","/authenticationServiceDocked.jar"]