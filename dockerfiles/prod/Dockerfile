FROM openjdk:17
COPY target/cenord-prod.jar cenord-prod.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","cenord-prod.jar"]