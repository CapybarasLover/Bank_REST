--liquibase formatted sql

--changeset petrm:1.0.1-create-status-enum

CREATE TYPE status AS ENUM('ACTIVE', 'BLOCKED', 'EXPIRED');

--rollback DROP TYPE status;

--changeset petrm:1.0.1-init-cards


CREATE TABLE cards(
    id              BIGINT              GENERATED ALWAYS AS IDENTITY        PRIMARY KEY,
    card_number     varchar(19)         NOT NULL        UNIQUE,
    cardholder      BIGINT              NOT NULL,
    valid_date      DATE                NOT NULL,
    status          status              NOT NULL    DEFAULT 'ACTIVE',
    balance         DECIMAL(19,2)       NOT NULL       DEFAULT 0.00        CHECK(balance>=0),

    CONSTRAINT      fk_cardholders
        FOREIGN KEY (cardholder)
        REFERENCES users(id) ON DELETE CASCADE
);

--rollback DROP TABLE cards;