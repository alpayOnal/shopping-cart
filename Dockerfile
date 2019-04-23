FROM maven:3.6-jdk-8

WORKDIR /app
COPY pom.xml /tmp/pom.xml

ADD . /app
RUN mvn package install
