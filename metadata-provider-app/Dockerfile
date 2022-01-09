FROM openjdk:17-oracle
COPY target/metadata-provider-app-0.1-SNAPSHOT-fat.jar /usr/lib/contexts/metadata-provider-app.jar
CMD java ${JAVA_OPTS} -jar /usr/lib/contexts/metadata-provider-app.jar