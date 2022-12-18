FROM openjdk:11
LABEL maintainer="Ahmet Hakan Billur"
ADD target/readingisgood-0.0.1-SNAPSHOT.jar readingisgood.jar
ENTRYPOINT ["java", "-jar", "readingisgood.jar"]