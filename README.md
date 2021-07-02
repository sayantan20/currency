# Currency Exchange Micro-Service

## Getting Started

### Basic - currency-conversion-service,currency-exchange-service

```
docker network create currency-network
docker run -p 8080:8080 --network=currency-network --name=conversion-service  -d conversion-service:latest
docker run -p 9090:9090 --network=currency-network --name=exchange-service -d exchange-service:latest
```

### Naming Server (Eureka Server), Config-Server, Netflix Zuul API Gateway

```
docker network create currency-network
docker run -p 8888:8888 --network=currency-network --name=config-server -d config-server:latest
docker run -p 8761:8761 --network=currency-network --name=eureka-server -d eureka-server:latest
docker run -p 8765:8765 --network=currency-network --name=netflix-zuul-api -d netflix-zuul-api:latest
docker run -p 8080:8080 --network=currency-network --name=conversion-service  -d conversion-service:latest
docker run -p 9090:9090 --network=currency-network --name=exchange-service -d exchange-service:latest

```

### Zipkin, Naming Server & Zuul, Config-Server

```
docker network create currency-network
docker run -p 5672:5672 -p 15672:15672 --network currency-network --name rabbitmq rabbitmq:3.5.3-management
docker run -p 9411:9411 --env RABBIT_URI=amqp://guest:guest@rabbitmq:5672 --network currency-network --name zipkin openzipkin/zipkin
docker run -p 8888:8888 --network=currency-network --name=config-server -d config-server:latest
docker run -p 8761:8761 --network=currency-network --name=eureka-server -d eureka-server:latest
docker run -p 8765:8765 --network=currency-network --name=netflix-zuul-api --env RABBIT_URI=amqp://guest:guest@rabbitmq:5672 -d netflix-zuul-api:latest
docker run -p 8080:8080 -p 8081:8081 -p 8082:8082 --network=currency-network --name=conversion-service --env RABBIT_URI=amqp://guest:guest@rabbitmq:5672 -d conversion-service:latest
docker run -p 9090:9090 -p 9091:9091 -p 9092:9092 --network=currency-network --name=exchange-service --env RABBIT_URI=amqp://guest:guest@rabbitmq:5672 -d exchange-service:latest
```

## Using docker-compose
```
version: '3.7'
services:

  rabbitmq:
    image: rabbitmq:3.8.5-management
    ports:
      - "5672:5672"
      - "15672:15672"
    restart: always
    healthcheck:
      test: [ "CMD", "rabbitmqctl", "status" ]
      interval: 20s
      timeout: 5s
      retries: 10
    networks:
      - currency-network

  zipkin-server:
    image: openzipkin/zipkin
    container_name: zipkin
    environment:
      STORAGE_TYPE: mem
      RABBIT_URI: amqp://guest:guest@rabbitmq:5672
    ports:
      - "9411:9411"
    restart: always
    depends_on:
      - rabbitmq
    networks:
      - currency-network

  config-server:
    build:
      context: config-server
      dockerfile: Dockerfile
    restart: always
    environment:
      PASSWORD: ******
    ports:
      - "8888:8888"
    networks:
      - currency-network

  netflix-zuul-api:
      build:
        context: zuul
        dockerfile: Dockerfile
      restart: always
      environment:
        RABBIT_URI: amqp://guest:guest@rabbitmq:5672
      ports:
        - "80:8765"
      depends_on:
        rabbitmq:
          condition: service_healthy
      links:
        - config-server
        - eureka-server
        - rabbitmq
      networks:
        - currency-network

  eureka-server:
      build:
        context: eureka
        dockerfile: Dockerfile
      restart: on-failure
      ports:
        - "8761:8761"
      networks:
        - currency-network

  conversion-service:
    build:
      context: conversion
      dockerfile: Dockerfile
    restart: always
    environment:
      RABBIT_URI: amqp://guest:guest@rabbitmq:5672
    ports:
      - "8080:8080"
      - "8081:8081"
      - "8082:8082"
    depends_on:
      rabbitmq:
        condition: service_healthy
      zipkin-server:
        condition: service_healthy
    links:
      - config-server
      - eureka-server
      - exchange-service
      - netflix-zuul-api
      - rabbitmq
      - zipkin-server
    networks:
      - currency-network


  exchange-service:
    build:
      context: exchange
      dockerfile: Dockerfile
    restart: always
    environment:
      RABBIT_URI: amqp://guest:guest@rabbitmq:5672
    ports:
      - "9090:9090"
      - "9091:9091"
      - "9092:9092"
    depends_on:
      rabbitmq:
        condition: service_healthy
      zipkin-server:
        condition: service_healthy
    links:
      - config-server
      - eureka-server
      - netflix-zuul-api
      - rabbitmq
      - zipkin-server
    networks:
      - currency-network

networks:
  currency-network:

```