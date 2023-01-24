FROM maven AS MAVEN_BUILD

MAINTAINER tokhirjon007@gmail.com

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package

FROM altairbob/jdk-17-sshd

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/*.jar /app/discovery-server.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/discovery-server.jar"]