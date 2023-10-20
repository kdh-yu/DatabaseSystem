-- Active: 1697603347199@@127.0.0.1@5432@calendar@public
CREATE TABLE calendar.event (
    EventId SERIAL PRIMARY KEY,
    UserID BIGINT NOT NULL,
    Name VARCHAR(50) NOT NULL,
    Description TEXT NULL DEFAULT NULL,
    Start_date DATE NOT NULL,
    start_time TIME NOT NULL,
    End_date DATE NOT NULL,
    end_time TIME NOT NUll,
    AllDay BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_userid FOREIGN KEY (UserID) REFERENCES calendar.user (UserID)
);