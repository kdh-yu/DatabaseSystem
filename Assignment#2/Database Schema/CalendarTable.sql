CREATE TABLE calendar.calendar (
    CalendarID SERIAL PRIMARY KEY,
    Name VARCHAR(20) NOT NULL,
    Description TEXT NULL DEFAULT NULL,
    Timezone TIMESTAMP WITH TIME ZONE
);