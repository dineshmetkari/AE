from openjdk:8
ADD target/config-server-0.0.1-SNAPSHOT.jar configserver.jar
EXPOSE 8888
RUN bash -c 'touch /configserver.jar'
ENTRYPOINT ["java","-jar","configserver.jar"]