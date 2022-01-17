# # Build stage#
# FROM maven:3.6.0-jdk-11-slim AS build
# WORKDIR /home/app/
# COPY aui6/src src
# COPY aui6/pom.xml .
# RUN mvn -f pom.xml clean package -Dmaven.test.skip

# # Package stage
# FROM openjdk:11-jre-slim
# WORKDIR /usr/local/lib/
# COPY --from=build /home/app/target/aui6-0.0.1-SNAPSHOT.jar app.jar
# RUN mkdir upload
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","app.jar"]

# Package stage
FROM openjdk:11-jre-slim
WORKDIR /usr/local/lib/
COPY aplsp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]