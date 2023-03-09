FROM eclipse-temurin:17-jre-alpine
COPY /target/lotto-web.jar /lotto-web.jar
ENTRYPOINT ["java","-jar","/lotto-web.jar"]
