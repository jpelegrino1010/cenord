FROM openjdk:17
COPY target/cenord.jar cenord.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","cenord.jar"]