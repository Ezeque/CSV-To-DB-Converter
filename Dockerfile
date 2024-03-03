#CRIANDO ARQUIVO EXECUTAVEL
FROM maven:3-amazoncorretto-21 as builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

#EXECUTANDO PROJETO
FROM openjdk:21

ENV DATABASE_DRIVER= \
    DATABASE_URL= \
    DATABASE_PW= \
    DATABASE_USERNAME= \
    FILES_PATH=

WORKDIR /app

COPY --from=builder app/target/*jar app/pagamento.jar

RUN mkdir -p $FILES_PATH

EXPOSE 8080

CMD ["java", "-jar", "app/pagamento.jar"]
