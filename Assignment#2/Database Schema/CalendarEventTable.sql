CREATE TABLE calendar.calendar_event (
    CalendarID BIGINT NOT NULL,
    EventID BIGINT NOT NULL,
    Role VARCHAR(20) NOT NULL,
    CONSTRAINT fk_calendarid FOREIGN KEY (CalendarID) REFERENCES calendar.calendar (CalendarID),
    CONSTRAINT fk_eventid FOREIGN KEY (EventID) REFERENCES calendar.event (EventID)
)