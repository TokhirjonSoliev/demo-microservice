FROM maven AS MAVEN_BUILD

MAINTAINER tokhirjon007@gmail.com

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package

FROM altairbob/jdk-17-sshd

WORKDIR /app/product-service

ARG JAR_FILE=target/demo2-0.0.1-SNAPSHOT.jar
COPY  ${JAR_FILE} product-service.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "product-service.jar"]