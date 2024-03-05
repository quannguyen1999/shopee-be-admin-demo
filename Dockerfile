#User JDK 17
FROM openjdk:17-jdk-slim

#Author Maintain
MAINTAINER quannguyen

# Install CURL
RUN apt-get update && apt-get install -y curl

#Copy File Jar to Docker
COPY target/shopee-be-admin-demo-latest.jar shopee-be-admin-demo-latest.jar

#Excute command to run Spring boot
#Cmd Example: java -jar target/shopee-be-account-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ \
    "java", \
    "-jar", \
    "shopee-be-admin-demo-latest.jar" \
]

