# An example of Spring Boot + REST + Mongo [DRAFT]

This is just an example of a standalone REST microservice that uses some sample capabilities of 

- Spring Boot
- Spring Data
- Spring WEB MVC

## Getting Started

To run this project you must first have running locally mongodb database. The easiest way to do it is running the following docker command:

```
docker run --name users-db-1 -d -p 27017:27017 mongo:3.2.6
```

then you can just run the application using the following:

```
gradle bootRun
```

Enjoy the service ;)

```
curl localhost:8080/users
```
