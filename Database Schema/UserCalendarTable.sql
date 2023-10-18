CREATE TABLE calendar.user_calendar (
    UserID BIGINT NOT NULL,
    CalendarID BIGINT not NULL,
    CONSTRAINT fk_userid FOREIGN KEY (UserID) REFERENCES calendar.user (UserID),
    CONSTRAINT fk_calendarid FOREIGN KEY (CalendarID) REFERENCES calendar.calendar (CalendarID)
);