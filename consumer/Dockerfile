FROM openjdk:11-oracle AS jre

FROM jre AS runner

WORKDIR /app

COPY /target/consumer-1.jar /app/application.jar

ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8

ENTRYPOINT java -jar /app/application.jar
