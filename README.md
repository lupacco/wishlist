# Wishlist Microservice

### Optei por utilizar princípios de Clean Architecture, mas sem seguir rigidamente sua estrutura, pois seria desnecessário para este contexto e equivalente a "matar uma formiga com uma bazuca". A abordagem utilizada mantém o código organizado sem adicionar complexidade excessiva.

## Endpoints

### POST /v1/wishlist/{clientId}/add/{productId}

Adiciona um produto à wishlist de um cliente.

### DELETE /v1/wishlist/{clientId}/remove/{productId}

Remove um produto da wishlist de um cliente.

### GET /v1/wishlist/{clientId}

Retorna todos os produtos na wishlist de um cliente.

### GET /v1/wishlist/{clientId}/contains/{productId}

Retorna true ou false indicando se um produto está na wishlist do cliente.

## Acesso ao Swagger UI

### Para acessar a documentação interativa do Swagger, **suba** a aplicação e acesse:
 #### Swagger UI: <http://localhost:8080/swagger-ui/index.html>

## Como subir o ambiente

## Este microsserviço utiliza MongoDB como banco de dados. Você pode rodar uma instância do MongoDB localmente usando docker-compose.

Subindo o MongoDB com Docker

`docker-compose up -d`

Isso iniciará um contêiner MongoDB disponível na porta 27017.

## Configuração do docker-compose.yml:

```
version: '3.8'

services:
mongodb:
image: mongo:latest
container_name: wishlist-mongodb
restart: always
ports:
- "27017:27017"
environment:
MONGO_INITDB_DATABASE: wishlist-db
```

## Construção e Execução do Microsserviço

### Construir a aplicação

`mvn clean package`

Isso criará um arquivo .jar na pasta target/.

## Rodar a aplicação manualmente

`java -jar target/wishlist-*.jar`

## Executando com Docker

A aplicação está preparada para rodar via Docker.

### Configuração do Dockerfile:

```
FROM openjdk:21-jdk
WORKDIR /app
COPY target/wishlist-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```
## Criar e rodar o container Docker

```
docker build -t wishlist-service .
docker run -p 8080:8080 --name wishlist-service --network=host wishlist-service
```

#### Isso iniciará o microsserviço na porta 8080.

### Obrigado e até o próximo projeto!
