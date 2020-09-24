## Requirements
* JDK 1.11
* docker-compose

## How to run
- Build project with maven:
```
./mvnw clean package -DskipTests=true
```
- Copy the following files:
```
compose/**
/target/*.jar
Dockerfile
compose.yml
```
- Go to the directory with files and execute the command:
```
export UID && export GID && docker-compose -f compose.yml up -d
```

