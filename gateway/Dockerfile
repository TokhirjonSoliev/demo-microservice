FROM maven AS MAVEN_BUILD

MAINTAINER tokhirjon007@gmail.com

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package

FROM altairbob/jdk-17-sshd

WORKDIR /app/api-gateway

ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar
COPY  ${JAR_FILE} gateway.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gateway.jar"]