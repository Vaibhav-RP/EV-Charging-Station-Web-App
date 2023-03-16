# base docker image
FROM openjdk:11
LABEL maintainer="VaibhavPatilEVStation.net"
ADD build/libs/veridic-0.0.1-SNAPSHOT.jar EVSation-docker.jar
ENTRYPOINT [ "java","-jar","EVSation-docker.jar" ]