
# Auction App Web Application

## Tech Stack

**Client:** React, SCSS, Vite

**Server:** Spring Boot


## Prerequisites

Before you begin, ensure you have the following tools installed:
- Git
- Node.js and npm
- Java JDK 17
- Maven
- PostgreSQL

## Run Locally

Clone the project:

```bash
  git clone https://github.com/lejlamuratovic/AuctionApp
```
Navigate to the backend directory:

```bash
  cd auction-app-backend
```

Install dependencies:

```bash
  mvn install
```

Configure PostgreSQL Database: 
-  Make sure PostgreSQL is running.
- Create a new database named `auction_db` or adjust the `application.properties` file in the backend's resources to match your configuration.

Build and run the backend server:

```bash
  mvn spring-boot:run
```

Navigate to the frontend directory: 

```bash
  cd ../auction-app-frontend
```

Install dependencies: 

```bash
  npm install
```

Run the frontend application:

```bash
  npm run dev
```
