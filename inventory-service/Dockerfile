FROM maven AS MAVEN_BUILD

MAINTAINER tokhirjon007@gmail.com

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package

FROM altairbob/jdk-17-sshd

WORKDIR /app/inventory-service

ARG JAR_FILE=target/inventory-service-0.0.1-SNAPSHOT.jar
COPY  ${JAR_FILE} inventory-service.jar

EXPOSE 8084
ENTRYPOINT ["java", "-jar", "inventory-service.jar"]