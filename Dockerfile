FROM eclipse-temurin:17-jre-alpine
COPY /target/lotto.jar /lotto.jar
ENTRYPOINT ["java","-jar","/lotto.jar"]
