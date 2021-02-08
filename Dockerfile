FROM debian:latest

RUN apt-get update && \
    apt-get install -y wget
RUN wget https://javadl.oracle.com/webapps/download/GetFile/1.8.0_261-b12/a4634525489241b9a9e1aa73d9e118e6/linux-i586/jdk-8u261-linux-x64.tar.gz -O jdk-8u261-linux-x64.tar.gz
RUN tar -vzxf jdk-8u261-linux-x64.tar.gz

RUN mkdir /opt/jvm
RUN mv jdk1.8.0* /opt/jvm

RUN mkdir /opt/api-starter
COPY api-starter-0.0.1-SNAPSHOT.jar /opt/api-starter/api-starter.jar

CMD  /opt/jvm/jdk1.8.0_261/bin/java -jar /opt/api-starter/api-starter.jar
