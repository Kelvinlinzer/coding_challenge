## Latitude BNPL Coding Challenge Answers

1. The component for calculating max stock profit is in class com.latitude.genoapay.codingchallenge.service.profitmaximizer.ProfitMaximizer.
2. All test are spring boot end2end unit test, in CodingChallengeApplicationTests.

```aidl
curl ...
```

## Bonus requirements

### Create a docker container for the Spring boot application
A docker file is created to build the container. Follow the steps to build and run the container.

1. Build the application
```
mvn clean install
```
2. Build docker file
```
docker build --tag=latitude-code-challenge:latest .
```
3. Run the docker container
```
docker run -p8080:8080 latitude-code-challenge:latest
```
Then the application is accessable from port 8080.

### Create a simple React SPA to fetch and display the data



