FROM maven AS MAVEN_BUILD

MAINTAINER tokhirjon007@gmail.com

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package

FROM altairbob/jdk-17-sshd

WORKDIR /app/user-service

ARG JAR_FILE=target/user-service-0.0.1-SNAPSHOT.jar
COPY  ${JAR_FILE} user-service.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "user-service.jar"]