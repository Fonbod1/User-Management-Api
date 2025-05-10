# User-Management-Api
## This is a Spring Boot Rest API for user registration, authentication, and user account management.
It supports JWT-based authentication, user creation, viewing, updating, and deletion.
#
# Features
#
## The Use management API has the following features
+ User Signup and Signin
+ JWT Refresh Token
+ Token Refresh Endpoint
+ User Validation
+ Get All Users
+ Get User by ID
+ Update User and Delete User
+ Swagger API Documentation
#
# Tech Stack
## The following are the software that was used to create this API
+ Java 24
+ Spring Boot
+ Intellij
+ Postgresql
+ Spring Security
# 
# Setup Instructions
## git clone https://github.com/Fonbd1/User-Management-Api
#
## Access API Docs
http://localhost:8082/swagger-ui.html

# Authentication Endpoints
## Base URL: /api/v1/auth
|---------------------------------------------------------------------------------------| 
|     Method    |     Endpoint  |    Description                                        |
| ______________________________________________________________________________________|
| POST          |  /signup      |  Register a new user                                  |
| POST          |  /signin      |  Authenticate and get JWT                             |
| POST          |  /refresh     |  Refresh access token using refresh token             |
 ________________________________________________________________________________________
