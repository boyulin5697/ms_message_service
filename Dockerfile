FROM adoptopenjdk/openjdk11
VOLUME /tmp
MAINTAINER "by."
ADD build/libs/ms_message_service-0.0.1-SNAPSHOT.jar module.jar
EXPOSE 19002
ENTRYPOINT ["java","-jar","/module.jar"]
