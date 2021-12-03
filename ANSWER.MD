## Latitude BNPL Coding Challenge Answers

1. The component for calculating max stock profit is in class com.latitude.genoapay.codingchallenge.service.profitmaximizer.ProfitMaximizer.
2. All test are spring boot end2end unit test, in CodingChallengeApplicationTests.
3. Start the application in IDE, or follow the docker container steps below. And test the api with the following command.

```
curl -X POST localhost:8080/api/stock/getMaxProfit \
-H 'Content-Type: application/json' \
-d '{"id": 1,"stockPrices": [1,1,5, 6, 9, 11],	"startDateTime": "2021-12-02T10:00:00",	"endDateTime": "2021-12-02T11:00:00"}'
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
1. Change to web folder
2. Install the dependency packages
```
npm install
```
3. Start the application
```
npm run start
```

## Add Github Actions for CI
None
## Create a cloud formation template that will deploy the application to ECS Fargate
None
