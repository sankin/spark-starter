[![Build Status](https://travis-ci.org/sankin/spark-starter.svg?branch=master)](https://travis-ci.org/sankin/spark-starter)

# spark-starter

This example demonstrate the difference in performance between `reduceByKey` and `groupBy` operations when counting simple key-value pairs.

## Prerequisites

* Maven 3
* Java 8
* [Optional] brew install apache-spark

## To Build
`mvn clean install`

## To Run

#### Maven
`mvn spring-boot:run`

#### Java

`java -jar target/spark-starter-0.0.1-SNAPSHOT.jar`

## Application Parameters

#### Spark Home
Change the default value of `spark.home` in application.properties or by passing environment or JVM variable

`java -jar target/spark-starter-0.0.1-SNAPSHOT.jar -Dspark.home=/usr/local/Cellar/apache-spark/2.1.1/libexec`

## API

#### ReduceByKey
curl http://localhost:8080/api/reduceByKey?data=test,test,test,hello,data,data,one,more,time,time,more

#### GroupBy
curl http://localhost:8080/api/groupBy?data=test,test,test,hello,data,data,one,more,time,time,more

#### Results
Open the Spark Jobs ui (e.g. http://192.168.1.65:4040/jobs/) to view the execution performance for the different types of jobs.
