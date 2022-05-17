# api-transaction

Challenge en Java 11 en el que se utilizo lo siguiente:
- IntelliJ
- Postman

## Para compilar:

mvn clean package

## Generar y corren imagen en docker:

docker build -t api-transaction:latest .

docker run -d -p 8080:8080 api-transaction:latest

## Checkear que esta funcionando

http://localhost:8080/actuator/health

## Documentación

http://localhost:8080/swagger-ui/index.html

## Pruebas funcionales con postman

./api-transaction.postman_collection.json
