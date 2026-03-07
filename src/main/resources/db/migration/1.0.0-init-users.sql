--liquibase formatted sql

--changeset petrm:1.0.0-create-role

CREATE TYPE role AS ENUM('ROLE_USER', 'ROLE_ADMIN');

--rollback DROP TYPE role

--changeset petrm:1.0.0-init-users

CREATE TABLE users (
    id          BIGINT          GENERATED ALWAYS AS IDENTITY        PRIMARY KEY,
    username    VARCHAR(255)    NOT NULL                            UNIQUE,
    password    VARCHAR(255)    NOT NULL,
    role        role            NOT NULL                            DEFAULT 'ROLE_USER',
    enabled     BOOLEAN         NOT NULL                            DEFAULT TRUE
);

--rollback DROP TABLE users;