Staycon
=========

Social Network

##Description

[Staycon](http://localhost:8080/) is a basic social network web site based on Spring Boot. Currently it's under development.

##How To:
If you want to run the web-app on localhost, follow these steps:
* Create Database on MySql:
  * CREATE DATABASE **localstaycon**;
  * CREATE USER '**staycon**'@'localhost' IDENTIFIED BY '**staycon**';
  * GRANT ALL PRIVILEGES ON localstaycon.* TO 'staycon'@'localhost' WITH GRANT OPTION; 
  
* Clone the project 
* $ **mvn package**
* $ **mvn spring-boot:run**
* Visit the site: **http://localhost:8080**