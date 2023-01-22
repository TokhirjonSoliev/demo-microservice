FROM maven AS MAVEN_BUILD

MAINTAINER mustofakodirov@gmail.com

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package

FROM altairbob/jdk-17-sshd

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/demo2-0.0.1-SNAPSHOT.jar /app/
RUN addgroup -S springdocker && adduser -S springdocker -G springdocker
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "demo2-0.0.1-SNAPSHOT.jar"]