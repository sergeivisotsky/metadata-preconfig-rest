FROM openjdk:11-jdk
COPY target/metadata-deployer-0.1-SNAPSHOT-fat.jar /usr/lib/contexts/metadata-deployer.jar
CMD java ${JAVA_OPTS} -jar /usr/lib/contexts/metadata-deployer.jar