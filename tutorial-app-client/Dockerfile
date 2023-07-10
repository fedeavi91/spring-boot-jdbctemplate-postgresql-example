FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/tutorial-app-client-1.0.jar tutorial-app-client.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tutorial-app-client.jar"]
