FROM openjdk:17-alpine
ARG JAR_FILE=/build/libs/*.jar

WORKDIR /app

# jar 파일 호스트에서 컨테이너로 복사
COPY ${JAR_FILE} /app/app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app/app.jar"]
#ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "/app/app.jar"]