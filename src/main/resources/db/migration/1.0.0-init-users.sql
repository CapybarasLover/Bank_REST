--liquibase formatted sql
--changeset petrm:1.0.0-init

-- TODO доделать чейнжсеты (добавить ост поля и еще таблицы)
CREATE TABLE users (
    id          BIGSERIAL       PRIMARY KEY,
    username    VARCHAR(255)    NOT NULL    UNIQUE,
)