Before running the image of the application:

- A mongo database container must be created with the name: mongo

MVN commands builds the image for the docker

To run everything this is the docker command:

docker run -p 8084:8080 --name rest_spring-boot_mongo_tutorial --link=mongo povedof/rest_spring-boot_mongo_tutorial

