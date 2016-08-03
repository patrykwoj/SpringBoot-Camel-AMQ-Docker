FROM java:8
VOLUME /tmp
ADD boot-docker-camel-demo-0.1.0.jar app.jar

EXPOSE 8080

RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

