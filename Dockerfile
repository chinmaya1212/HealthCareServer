FROM openjdk:8
EXPOSE 9094
ADD target/Healthcare.jar Healthcare.jar
ENTRYPOINT ["java","-jar","/Healthcare.jar"]