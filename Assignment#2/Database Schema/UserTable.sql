-- Active: 1697603347199@@127.0.0.1@5432@calendar@public
CREATE TABLE calendar.user (
    UserId SERIAL PRIMARY KEY,
    Name VARCHAR(20) NOT NULL,
    Password VARCHAR(20) NOT NULL,
    NotificationChannel VARCHAR(20) NOT NULL
); 