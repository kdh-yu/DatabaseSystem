-- Active: 1697603347199@@127.0.0.1@5432@calendar

CREATE TABLE calendar.user (
    UserId VARCHAR(20) PRIMARY KEY,
    Name VARCHAR(20) NOT NULL,
    Password VARCHAR(30) NOT NULL
); 

SELECT * from calendar.user;

drop table calendar.user;