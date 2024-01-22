FROM openjdk:17-alpine
COPY target/shopping-cart-service-0.0.1-SNAPSHOT.jar shopping-cart-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/shopping-cart-service-0.0.1-SNAPSHOT.jar"]