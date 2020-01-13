FROM java:8
ADD target/follow-service.jar follow-service.jar
ENTRYPOINT ["java","-jar","follow-service.jar"]
