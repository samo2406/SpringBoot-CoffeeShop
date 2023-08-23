# SpringBoot-CoffeeShop
Java microservices for a fictional coffee shop

Includes two microservices :

- Orders service (OrderService.java)
- Barista service (BaristaService.java)

The orders are persisted using a H2 database. The database table model is defined in OrderModel.java

Once the order is finalized (saved to the database), the barista service gets notified. The barista service then processes the order, advancing the order state every X seconds (currently 10). Once the order reaches it's final state, orders service gets notified. The orders service then deletes the "picked up" order from the database.

Internal communication between these microservices is implemented in form of REST web services.

Used technologies :

- Java 17
- Spring Boot 3.1.2
- Maven
- H2
