FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8003
ADD target/*.jar app3.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app3.jar" ]