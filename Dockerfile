FROM ubuntu:16.04

# This is in accordance to : https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-get-on-ubuntu-16-04
RUN apt-get update && \
	apt-get install -y openjdk-8-jdk && \
	apt-get install -y curl && \
#	apt-get install -y telnet && \
#	apt-get install -y mysql-client && \
#	apt-get install -y gradle && \
#	apt-get install -y wget && \
#	apt-get install -y ant && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

ADD . /usr/local/app

# Not able to build Scala code with gradle on docker, perhaps plugin issue... Missing Maven
#RUN wget www.scala-lang.org/files/archive/scala-2.12.5.deb && dpkg -i scala-2.12.5.deb && rm scala-2.12.5.deb
#RUN cd /usr/local/app && gradle build

# planB build locally copy to docker image... lean and clean
RUN mkdir /usr/local/FinchSlickGradleAPIV1 && \
    cp /usr/local/app/build/libs/FinchSlickGradleAPI-1.0-SNAPSHOT.jar /usr/local/FinchSlickGradleAPIV1

WORKDIR /usr/local/FinchSlickGradleAPIV1

# All set, start the application... The application will automatically create schema (i.e. create any missing tables)
CMD ["java", "-cp", "FinchSlickGradleAPI-1.0-SNAPSHOT.jar", "com.srilabs.Main"]
