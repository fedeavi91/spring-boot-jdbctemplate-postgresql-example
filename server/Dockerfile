FROM openjdk:17
VOLUME /tmp
EXPOSE 8081
COPY target/tutorial-app-server-1.0.jar tutorial-app-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tutorial-app-server.jar"]
