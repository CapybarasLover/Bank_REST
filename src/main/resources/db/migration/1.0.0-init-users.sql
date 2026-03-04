--liquibase formatted sql
--changeset petrm:1.0.0-users

-- TODO доделать чейнжсеты (добавить ост поля и еще таблицы)
CREATE TABLE users (
    id          BIGINT          GENERATED ALWAYS AS IDENTITY        PRIMARY KEY,
    username    VARCHAR(255)    NOT NULL                            UNIQUE,
    password    VARCHAR(255)    NOT NULL,
    role        VARCHAR(50)     NOT NULL                            DEFAULT 'USER',
    enabled     BOOLEAN         NOT NULL                            DEFAULT TRUE
);

--rollback DROP TABLE users;