FROM openjdk:12
WORKDIR /
ADD build/libs/FontysIN-1.0-SNAPSHOT-all.jar fontysin.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "fontysin.jar"]