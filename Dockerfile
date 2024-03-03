FROM openjdk:21

ENV DATABASE_DRIVER=com.mysql.cj.jdbc.Driver \
    DATABASE_URL=jdbc:mysql://host.docker.internal:3306/teste \
    DATABASE_PW=152223 \
    DATABASE_USERNAME=root \
    FILES_PATH=src/filesPath

WORKDIR /app

COPY target/*jar app/pagamento.jar

RUN mkdir -p $FILES_PATH

EXPOSE 8080

CMD ["java", "-jar", "app/pagamento.jar"]
