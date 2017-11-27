FROM openjdk:8
ADD target/spring-boot-demo-0.0.1-SNAPSHOT.jar redis.jar
EXPOSE 8100
RUN bash -c 'touch /redis.jar'
ENTRYPOINT ["jar","-jar","redis.jar"]