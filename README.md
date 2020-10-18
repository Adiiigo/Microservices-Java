### Microservices-Java

### Repo Information

#### Spring Config Server - localconfig
  - for external configuarions 
  - Has configurations for dev, qa and prod

#### Spring Config Client - limits-service 
  - uses the external configuration from the git repo 
  - does not depend on applications.properties of itself for setting the configuration
 
#### JPA connection - currency-exchange-service
  - is used for for providing currency conversion rate from one currency to another currency
  - has jpa connection to the in-memory database - h2
  
#### Rest Template and feign Client - currency-conversion-service
 - is used to connect to currency-exchange-service to get conversion rate and do the conversion
 - used rest template to pass the data ans then used feign client to remove the bolierplate code

#### Client side load balancing - Ribbon - currency-conversion-service
 - since currency-conversion-service calls currency-exchange-service, we have made multiple replicas of the currency-exchange-service, and have hardcoded the endpoints of it in currency-conversion-service application.properties file. 
  - Is it used for client side load balancing.
  
 #### Client side load balancing - Eureka+Ribbon - netflix-eureka-naming-server
  - to remove the hardcoded enpoints of currency-exchange-service 
  - let currency-conversion-service discover the working endpoints if currency-exchange-service, we register all the services to the eureka server(service discovery)
  
#### ZUUL API Gateway - Server Side Load Balacing
 - the external client interacting with the application are not aware of the different services running on ther server 
 - provides and common point for the clients to interact
 - vulnerbale to single point of failure(use duplicates)
 
#### Spring cloud Sleuth 
 - Provides a unique ID to all the request which are generated between communication of microservices.
 - This helps in identification of particular request in the logs and find traces
 
#### Rabbit MQ 
 - added pom dependency in all the microservices so that they can send the logs to centralized server through mq broker
 
#### Distributed Tracing - Zipkin
 - microservices are dependent on each other. 
 - If one fails, it can affect other services. 
 - Hence, we have to keep track of all the request in one centralized server which will help to get all the information we needed while finding the fault
 - Is connected to a database(currecntly, h2 database), to store all the logs
 
#### Spring Cloud Bus
 - While keeping external configuration 
 - If we have 100s of replicas of particular service refreshing it for every replica becomes difficult 
 - Hence, spring cloud bus dependency

#### Fault Tolerance -Hystrix
 - As services are dependent on each other.
 - If one fails, it will create fallback loop and it will keep cascading till it will create a delay in result which has to be displayed client.
 - Using fallback method instead to inform thr user that there is goign to be delay.
