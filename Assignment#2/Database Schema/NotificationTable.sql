CREATE TABLE calendar.notification (
    NotificationID SERIAL PRIMARY KEY,
    Effective_date DATE NOT NULL,
    Effective_time TIME NOT NULL,
    Interval BIGINT NOT NULL,
    UserID BIGINT NOT NULL,
    CONSTRAINT fk_userid FOREIGN KEY (UserID) REFERENCES calendar.user (UserID)
);
