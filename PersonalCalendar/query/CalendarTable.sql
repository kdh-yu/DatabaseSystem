CREATE TABLE calendar.calendar (
    calendarid SERIAL PRIMARY KEY,
    timezone TIMESTAMP WITH TIME ZONE
);

select * from calendar.calendar;

alter table calendar.calendar alter COLUMN timezone drop not null;

INSERT INTO calendar.calendar (calendarid) VALUES (1);

alter table calendar.calendar RENAME COLUMN calendarid TO userid;