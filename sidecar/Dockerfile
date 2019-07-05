FROM openjdk:8-jre-alpine

LABEL maintainer="tanjian@apache.org"

ENV TZ=Asia/Shanghai \
    DIST_NAME=sidecar-1.0.0

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

COPY target/"$DIST_NAME.jar" /"$DIST_NAME.jar"

EXPOSE 5678

ENTRYPOINT java $JAVA_OPTS -jar /$DIST_NAME.jar
