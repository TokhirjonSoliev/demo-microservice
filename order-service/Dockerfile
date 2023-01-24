FROM maven AS MAVEN_BUILD

MAINTAINER tokhirjon007@gmail.com

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package

FROM altairbob/jdk-17-sshd

WORKDIR /app/order-service

ARG JAR_FILE=target/order-service-0.0.1-SNAPSHOT.jar
COPY  ${JAR_FILE} order-service.jar

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "order-service.jar"]