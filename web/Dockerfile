

# 도커허브에서 jdk 다운
FROM adoptopenjdk/openjdk11

MAINTAINER kang

#docker 내부에 api 폴더를 만듬
WORKDIR /api

# 변수를 하나지정, 현재 내 폴더에서 build 폴더 안에 jar 파일을 찾아서,
ARG JAR_FILE=build/libs/*.jar

# app.jar로 복사해서  /api 폴더 안에 위치
COPY ${JAR_FILE} app.jar

# 컨테이너 port 노출
EXPOSE 9000

# 위에 다하고 나서, prod profile을 참고해서 app.jar를 실행
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]


