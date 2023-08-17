FROM openjdk:19
WORKDIR /app
EXPOSE 8909
ADD target/github-data-emailer-v1.0.jar github-data-emailer-v1.0.jar
ENTRYPOINT ["java", "-jar", "/app/github-data-emailer-v1.0.jar"]


