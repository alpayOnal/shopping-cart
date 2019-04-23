# Shopping Cart

## Requirements


- JDK 1.8_0_211
- Maven 3.6

## Install

    docker-compose build
    docker-compose up -d


## Start
   
    docker-compose exec app
    mvn exec:java -Dexec.mainClass="com.shoppingcart.Main"

## Run Test

    docker-compose exec app
    mvn test
    


You can see Coverage report target/site/jacoco/site/index.html.


## Sample Outputs

![Alt text](sample_outputs/result.png?raw=true "Cart Info")

![Alt text](sample_outputs/tests.png?raw=true "Test Report")


![Alt text](sample_outputs/tests_coverage.png?raw=true "Coverage Report")
