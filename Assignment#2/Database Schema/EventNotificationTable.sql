CREATE TABLE calendar.event_notification (
    EN_ID SERIAL PRIMARY KEY,
    EventID BIGINT NOT NULL,
    NotificationID BIGINT NOT NULL,
    CONSTRAINT fk_eventid FOREIGN KEY (EventID) REFERENCES calendar.event (EventID)
);