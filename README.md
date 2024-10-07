# Cinema Room Application

A simple cinema seat booking application built with Spring Boot and Thymeleaf. Users can view available seats, purchase tickets, return tickets, and administrators can view statistics.

> [!IMPORTANT]
> Visit [Cinema Room Web Application](https://qrcode-8wmm.onrender.com/api/) to access the application.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Deployment](#deployment)
- [Usage](#usage)
  - [Welcome Page](#welcome-page)
  - [Viewing Seats](#viewing-seats)
  - [Purchasing a Seat](#purchasing-seat)
  - [Returning a Ticket](#returning-ticket)
  - [Admin Statistics](#admin-statistics)
- [Security](#security)
- [Requirements](#requirements)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
- [License](#license)

## Features

- Welcome Page: Provides an introduction to the application.
- View Seats: Displays available and purchased seats in a visual format.
- Purchase Tickets: Users can purchase available seats.
- Return Tickets: Users can return purchased tickets on a dedicated page.
- Admin Login: Secure login for administrators.
- Statistics Page: Admins can view statistics about ticket sales.

## Technologies

This project uses the following technologies:

- Java 17 or higher
- Spring Boot 2.5.5
- Thymeleaf 3
- Spring Security 5
- Bootstrap 4
- Maven
- Docker

## Deployment

The Cinema Room application is deployed and accessible online.

### Accessing the Deployed Application

URL: [https://cinema-room.onrender.com](https://cinema-room.onrender.com)

You can access the application using the link above. All the functionalities described are available in the deployed version.

## Usage

### Accessing the Application
- Web Browser: Open any modern web browser (e.g., Chrome, Firefox, Edge).
- Navigate to: [https://cinema-room.onrender.com](https://cinema-room.onrender.com)
  
### Welcome Page
- Description: The landing page provides an introduction and overview of the application features.
- Navigation: Use the navigation bar at the top to access different sections of the application.

### Viewing Seats
- Access: Click "View Seats" in the navigation bar.
- Description: Displays a table representing the cinema seating layout.
  - Rows: Each row is labeled (e.g., Row 1, Row 2).
  - Seats: Each seat is represented by a button.
    - Green Buttons: Available seats.
    - Red Buttons: Purchased seats.
- Actions:
  - Hover over seats to see seat details.
  - Click on a green seat button to initiate purchase.
    
### Purchasing a Seat

Steps:
- On the Seats page, locate an available seat (green button).
- Click on the desired seat.
- A confirmation message will appear at the top of the page:
  - Success: "Seat purchased successfully!"
  - Failure: "Seat already purchased or invalid seat!"
- The seat button will turn red, indicating it is now purchased.
> [!NOTE]
> The page will refresh to show the updated seating chart.


### Returning a Ticket
- Access: Click on "Return Ticket" in the navigation bar.
- Description: Provides a form to return a purchased ticket.
- Steps:
  - Enter the Ticket ID in the form provided.
    - Ticket ID Format: `row_column` (e.g., `1_1` for Row 1, Seat 1).
  - Click the "Return Ticket" button.
  - A message will appear:
    - Success: "Seat returned successfully!"
    - Failure: "Invalid seat ID or seat is already available!"
> [!NOTE]
> After returning a ticket, you can navigate back to the Seats page to see the seat now available (green button).

### Admin Statistics
- Access: Click on "Admin Login" in the navigation bar.
- Description: Secure login page for administrators.
- Login Credentials:
  - Username: admin
  - Password: login
- Steps:
  - Enter the username and password.
  - Click the "Login" button.
  - enter `admin` for `login` and `admin` for `password`
  - Upon successful login, you will be redirected to the Statistics page.

### Statistics Page

- Description: Displays cinema room statistics.
- Information Available:
  - Total Seats: Total number of seats in the cinema.
  - Purchased Tickets: Number of tickets sold.
  - Percentage of Seats Sold: Shows how much of the cinema is booked.
  - Current Income: Total revenue from ticket sales.
- Navigation: Use the navigation bar to return to other pages.

## Security

### Spring Security is used for authentication and authorization.
#### Admin Area:
- Restricted to users with the ADMIN role.
- Only authenticated admins can access the /statistics endpoint.
#### User Area:
- Pages like viewing seats and returning tickets are accessible to all users without authentication.
#### CSRF Protection:
- Disabled for simplicity in this application.
- Can be enabled and configured as needed for enhanced security.

## Requirements
### Requirements for running project locally

- Java Development Kit (JDK) 17
- Maven 3.6 or higher
- Docker (optional, for containerized deployment)
- An IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor

## Installation

Clone the Repository
```
git clone https://github.com/rafalzbanski/cinema-room.git
```
Navigate to the Project Directory
```
cd cinema-room
```
Build the Project with Maven
```
mvn clean package
```

## Project Structure

```
cinema-room/
├── pom.xml
├── Dockerfile
├── src
│   ├── main
│   │   ├── java
│   │   │   └── pl/rafalzbanski/cinema_room
│   │   │       ├── controllers
│   │   │       │   └── SeatController.java
│   │   │       ├── models
│   │   │       │   └── Seat.java
│   │   │       ├── services
│   │   │       │   └── SeatService.java
│   │   │       ├── security
│   │   │       │   └── SecurityConfig.java
│   │   │       └── CinemaRoomApplication.java
│   │   └── resources
│   │       ├── templates
│   │       │   ├── fragments
│   │       │   │   └── navbar.html
│   │       │   ├── welcome.html
│   │       │   ├── seats.html
│   │       │   ├── return_ticket.html
│   │       │   ├── login.html
│   │       │   └── statistics.html
│   │       └── application.properties
└── README.md
```

## Running the Application
### Using Maven
```
mvn spring-boot:run
```
### Running the Packaged JAR
```
java -jar target/cinema-room-1.0.0.jar
```
### Using Docker
#### Build the docker image
```
docker build -t cinema-room .
```
#### Run the Docker Container
```
docker run -p 8080:8080 cinema-room
```
Access the application at [http://localhost:8080/](http://localhost:8080/)

## Endpoints

The Cinema Room application provides the following endpoints for interaction:

### Public Endpoints
- GET /
  - Description: Displays the welcome page with information about the application.
  - Example: https://cinema-room.onrender.com/
  
- GET /seats
  - Description: Displays the seating layout, showing available and purchased seats.
  - Example: https://cinema-room.onrender.com/seats
    
- POST /purchase
  - Description: Processes the purchase of a seat.
  - Parameters:
  - `row` (integer): The row number of the seat to purchase.
  - `column` (integer): The column number of the seat to purchase.
    
- GET /return
  - Description: Displays the return ticket page with a form to enter the ticket ID.
  - Example: https://cinema-room.onrender.com/return
- POST /return
  - Description: Processes the return of a purchased ticket.
  - Parameters:
    - `id` (string): The ticket ID in the format `row_column` (e.g., `1_1`).

### Authentication Endpoints
- GET /login
  - Description: Displays the admin login page.
  - Example: https://cinema-room.onrender.com/login
    
- POST /login
  - Description: Processes admin login.
  - Parameters:
  - `username` (string): Admin username.
  - `password` (string): Admin password.

#### Admin Endpoints (Requires Authentication)
- GET /statistics
  - Description: Displays statistics about ticket sales and cinema occupancy.
  - Example: https://cinema-room.onrender.com/statistics

## License

This project is licensed under the MIT License. See the [LICENSE](https://choosealicense.com/licenses/mit/) for more details.
