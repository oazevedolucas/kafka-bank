# Kafka Blank

Example Project using Spring Boot and Kafka with Docker.

## Build

To build this project you need Java 11 and Docker installed.

### Step by Step

First step is Run Docker command in terminal: 

```
docker-compose up
```

It will create Kafka service and PostgreSQL in a container on Docker.

Afterwards, run the project with Java 11. The project have migrations and creates the tables in the DB.
