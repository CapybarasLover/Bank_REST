--liquibase formatted sql
--changeset petrm:1.0.0-init-users

-- TODO изменить тип поля role на enum
CREATE TABLE users (
    id          BIGINT          GENERATED ALWAYS AS IDENTITY        PRIMARY KEY,
    username    VARCHAR(255)    NOT NULL                            UNIQUE,
    password    VARCHAR(255)    NOT NULL,
    role        VARCHAR(50)     NOT NULL                            DEFAULT 'USER',
    enabled     BOOLEAN         NOT NULL                            DEFAULT TRUE,
    CONSTRAINT  chk_user_role   CHECK (role IN ('USER', 'ADMIN'))
);

--rollback DROP TABLE users;