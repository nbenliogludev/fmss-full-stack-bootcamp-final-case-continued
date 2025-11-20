
# Final project - FMSS Bilisim Teknoloji Fullstack Web Bootcamp

This project is the final project of the FMSS Bilisim Teknoloji Fullstack Web Bootcamp. It was developed using a microservice architecture.

Website: [nbenlioglu.dev](https://www.nbenlioglu.dev/)<br>
LinkedIn: [Nikolay Benlioglu](https://www.linkedin.com/in/nikolay-benlioglu/)<br>
GitHub: [nbenliogludev](https://github.com/nbenliogludev)<br>
Email: [nikbenlioglu@gmail.com](mailto:nikbenlioglu@gmail.com)

## Key Features

- Each core service has its own database.
- Asynchronous messaging is handled via RabbitMQ.
- MongoDB is used for logs.
- Eureka is used for service discovery.
- A simple frontend is built with Next.js.
- The APIs can be tested via Postman.

## Try it with Postman

You can try the API endpoints with Postman: [**Postman API** 🚀](https://documenter.getpostman.com/view/5602393/2sA3kVj1Xz)

## Microservices

Below is the list of microservices in the project.<br>
<br>📦 User Service
<br>📦 Authentication Service
<br>📦 Ad Service
<br>📦 Ad Package Service
<br>🐝 Log Aggregation Service
<br>⛩️ API Gateway
<br>🌐 Eureka Service Discovery


## Project Diagram

![Proje Diyagramı](images/fmss-project-diagram.png)

# Next.js

The Next.js project allows users to create, edit, delete, and view details of ads.

## Technologies Used

- **Next.js**
- **TypeScript**
- **React Query**
- **nextAuth**
- **Tailwind CSS**

## Start the Project with Docker

1. Open your terminal and go to the project's root directory.
2. Run `docker-compose up -d`.
3. Go to http://localhost:3000/discover in your browser.


## User Service - [User Service](user-service)

The User Service is responsible for user management. It allows creating, deleting, and updating users.

### API Adresleri

| Method | Adres                | Açıklama          |
|--------|----------------------|-------------------|
| `GET`  | `api/v1/users`       | Get all users     |
| `GET`  | `api/v1/users/{id}`  | Get user by ID    |
| `POST` | `api/v1/users`       | Create a new user |
| `PUT`  | `api/v1/users/{id}`  | Update a user     |
| `DELETE`| `api/v1/users/{id}`  | Delete a user     |


## Authentication Service - [Authentication Service](user-authentication)

AuthenticationService manages user authentication using JWT. It provides user registration, login, and logout operations.

### API Endpoints

| Method | Adres                      | Açıklama                      |
|--------|----------------------------|-------------------------------|
| `POST` | `api/v1/auth/register`     | Create a new user account     |
| `POST` | `api/v1/auth/authenticate` | Authenticate a user (login)   |



## Package Service - [Ad Service](ad-service)

The Ad Service allows users to create, update, delete, and list ads.

### API Endpoints

| Method   | Adres                   | Açıklama              |
|----------|-------------------------|-----------------------|
| `GET`    | `/api/v1/ads`           | Get all ads           |
| `GET`    | `/api/v1/ads/{id}`      | Get ad by ID          |
| `GET`    | `/api/v1/ads/user/{id}` | Get ads by user ID    |
| `POST`   | `/api/v1/ads`           | Create a new ad       |
| `PUT`    | `/api/v1/ads/{id}`      | Update an existing ad |
| `DELETE` | `/api/v1/ads/{id}`      | Delete an existing ad |

# Package Service - [Ad Package Service](ad-package-service)

The Package Service manages users’ ad publishing rights. Users can purchase packages that grant them the right to publish a certain number of ads for a certain period. The Package Service allows creating, updating, deleting, and viewing packages.
``
### API Endpoints

| Method   | Adres                          | Açıklama                   |
|----------|--------------------------------|----------------------------|
| `GET`    | `/api/v1/adPackages`           | Get all packages           |
| `GET`    | `/api/v1/adPackages/{id}`      | Get package by ID          |
| `GET`    | `/api/v1/adPackages/user/{id}` | Get packages by user ID    |
| `POST`   | `/api/v1/adPackages`           | Create a new package       |
| `PUT`    | `/api/v1/adPackages/{id}`      | Update an existing package |
| `DELETE` | `/api/v1/adPackages/{id}`      | Delete an existing package |``


## Log Aggregation Service - [Log Aggregation Service](log-aggregation-service)

The Log Aggregation Service collects info and error logs produced by other microservices and stores them in a MongoDB database. It exposes an API to access these logs. Mongo Express is also included in the project to manage your MongoDB database.

### API Endpoints

| Method | Adres                                      | Açıklama        |
|--------|--------------------------------------------|-----------------|
| `GET`  | `log-aggregation-service/api/v1/info-logs` | Get info logs   |
| `GET`  | `log-aggregation-service/api/v1/error-logs`| Get error logs  |

## API Gateway - [API Gateway](api-gateway)

The API Gateway exposes the APIs of other microservices through a single entry point. It also retrieves the service locations from Eureka.

# Eureka Sunucusu - [Eureka Server](eureka-server)

The Eureka Server is the service where other microservices register themselves and where their addresses are stored. The API Gateway obtains the addresses of other services via Eureka.

## Application UI

### "Discover all" Page
On this page, users can browse a list of all existing ads.

![Discover all ads](images/discover.png)

### “My Ads” Page
On this page, the ads created by the current user are listed.

![Discover all ads](images/my_ads.png)

### Create Ad
On this page, users can fill out a form to create a new ad.

![Discover all ads](images/create_ad.png)

### Packages Page
On this page, users can purchase ad packages. Packages grant the right to publish a certain number of ads for a certain period.

![Discover all ads](images/ad_package.png)

<br>

## FMSS Bilişim Teknoloji Fullstack Web Bootcamp

Website: [nbenlioglu.dev](https://www.nbenlioglu.dev/)<br>
LinkedIn: [Nikolay Benlioglu](https://www.linkedin.com/in/nikolay-benlioglu/)<br>
GitHub: [nbenliogludev](https://github.com/nbenliogludev)<br>
Email: [nikbenlioglu@gmail.com](mailto:nikbenlioglu@gmail.com)
