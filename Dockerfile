FROM ubuntu:focal

# Update Ubuntu
RUN apt-get update --fix-missing && apt-get -y upgrade

# Define mountable directories.
VOLUME ["/data/db"]

RUN mkdir /usr/api
WORKDIR /usr/api

#install jdk
RUN apt-get update && apt-get upgrade
RUN apt-get -y install openjdk-17-jdk-headless

COPY target/*.jar /usr/api/my-jar.jar

RUN chmod +x /usr/api/*.jar

EXPOSE  8888

#CMD /bin/bash
CMD ["java", "-jar", "/usr/api/my-jar.jar"]
