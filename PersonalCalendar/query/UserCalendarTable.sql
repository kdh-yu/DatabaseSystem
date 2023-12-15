-- Active: 1697603347199@@127.0.0.1@5432@calendar

CREATE TABLE calendar.usercalendar (
    calendarid SERIAL REFERENCES calendar.calendar (calendarid),
    userid VARCHAR(10) REFERENCES calendar.user (userid)
);

ALTER TABLE calendar.usercalendar ALTER COLUMN userid TYPE VARCHAR(20);

select * from calendar.usercalendar;

