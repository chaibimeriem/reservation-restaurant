# reservation-restaurant
# Restaurant Reservation System

## Description
This project is a restaurant reservation microservices management system built with spring boot

## Features
- Reservation management (create, update, cancel reservations).
- Restaurant management (add, update restaurant details, manage table availability).
- EST API with pagination, sorting and filters.
- API documentation via swagger.
- H2 database for development.

## Prerequisites

- Java 17 or later
- Maven (or Gradle if used)
- An IDE (like IntelliJ IDEA or Eclipse)

## Installation

1. Clone the repository:
    git clone https://github.com/chaibimeriem/restaurant-reservation-system.git
    cd restaurant-reservation-system


2. If you're using **Maven**, install dependencies and build the project by running:
    mvn clean install

3. Ensure your Java environment is correctly set up.

## Running the Application

1. To start the application, run the following command:

    mvn spring-boot:run

    Or if you're using an IDE, you can run the application by launching the main class `RestaurantReservationApplication`.

2. The application will start on [http://localhost:8080](http://localhost:8080).

## API Documentation (Swagger)

Once the application is running, you can access the API documentation at:



## API Request example with pagination
GET /api/reservations?page=0&size=5&sort=name,asc
