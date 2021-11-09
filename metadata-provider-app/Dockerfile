FROM openjdk:11-jdk
COPY target/metadata-provider-app-0.1-SNAPSHOT-fat.jar /usr/lib/contexts/metadata-provider-app.jar
CMD java ${JAVA_OPTS} -jar /usr/lib/contexts/metadata-provider-app.jar