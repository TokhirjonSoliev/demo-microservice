FROM maven AS MAVEN_BUILD

MAINTAINER tokhirjon007@gmail.com

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package

FROM altairbob/jdk-17-sshd

WORKDIR /app/discovery-server

ARG JAR_FILE="discovery-server-0.0.1-SNAPSHOT.jar"
ADD  ${JAR_FILE} discovery.jar

EXPOSE 8761
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar discovery.jar"]