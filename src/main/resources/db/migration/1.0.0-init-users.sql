--liquibase formatted sql

--changeset petrm:1.0.0-init-users

CREATE TABLE users (
    id          BIGINT          GENERATED ALWAYS AS IDENTITY        PRIMARY KEY,
    username    VARCHAR(255)    NOT NULL                            UNIQUE,
    password    VARCHAR(255)    NOT NULL,
    role        VARCHAR(15)     NOT NULL                            DEFAULT 'ROLE_USER',
    enabled     BOOLEAN         NOT NULL                            DEFAULT TRUE
);

--rollback DROP TABLE users;