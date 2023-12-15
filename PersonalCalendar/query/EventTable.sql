-- Active: 1697603347199@@127.0.0.1@5432@calendar

CREATE TABLE calendar.event (
    eventid SERIAL PRIMARY KEY,
    userid VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    description TEXT NULL DEFAULT NULL,
    start_date DATE NOT NULL,
    shour INT,
    sminute INT,
    end_date DATE NULL,
    ehour INT,
    eminute INT,
    allday BOOLEAN DEFAULT FALSE
);

ALTER TABLE calendar.event ALTER COLUMN end_time DROP NOT NULL;

DELETE FROM calendar.event WHERE eventid=2;

select * from calendar.event;

DELETE FROM calendar.event;

INSERT INTO calendar.event (userid, name, start_date, end_date) VALUES ('kdhh', '테스트', '12/06/2023', '12/07/2023');

SELECT * FROM calendar.calendar_event 

SELECT * FROM calendar.event
WHERE calendar_event.eventid = event.eventid;

DROP TABLE calendar.event;

SELECT * FROM calendar.event WHERE EXTRACT(MONTH FROM start_date) = 12 AND EXTRACT(DAY FROM start_date) = 6;