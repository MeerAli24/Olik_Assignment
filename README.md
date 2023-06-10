# Olik_Assignment

Technical Details:

Technology Stack: Spring Boot framework
Database: MySQL
IDE: Spring Tool Suite (STS)
Package Structure: Maintained standard package structure for better organization and code maintainability

API Endpoint: "/api/products"
This endpoint returns a list of all available products with details such as product name, image, cost for the specified duration.
I have implemented logic to exclude products that are already booked and have overlapping booking dates with the search duration.

API Endpoint: "/api/products/booking"
This endpoint allows the creation of a rental booking for a specific product and duration.
The booking details are saved in the database for future reference and tracking.

I have also tested the API endpoints using Postman, and everything is functioning as expected. The MySQL database is properly configured and connected to the application.
