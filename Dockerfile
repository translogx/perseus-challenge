FROM openjdk:11
EXPOSE 7001 7001
ADD . /usr
WORKDIR /usr

RUN apt-get install \
    wget \
    tar

RUN wget https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz

RUN tar -xvzf apache-maven-3.6.3-bin.tar.gz

RUN mv apache-maven-3.6.3 maven

RUN maven/bin/mvn package -f pom.xml

ENTRYPOINT [ "java", "-jar", "target/challenge-0.0.1-SNAPSHOT.jar"]