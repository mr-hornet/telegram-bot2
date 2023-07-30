-- liquibase formatted sql

-- changeset shershnev:create_notification_task2
CREATE TABLE notification_task2
(
    id SERIAL,
    message TEXT NOT NULL,
    chat_id BIGINT NOT NULL,
    notification_date_time TIMESTAMP NOT NULL
);