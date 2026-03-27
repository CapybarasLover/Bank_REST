--liquibase formatted sql

--changeset petrm:1.0.1-init-cards

CREATE TABLE cards(
    card_number     VARCHAR(19)         NOT NULL        UNIQUE              PRIMARY KEY,
    cardholder      BIGINT              NOT NULL,
    valid_date      DATE                NOT NULL,
    status          VARCHAR(10)         NOT NULL        DEFAULT 'ACTIVE',
    balance         DECIMAL(19,2)       NOT NULL        DEFAULT 0.00        CHECK(balance>=0),

    CONSTRAINT      fk_cardholders
        FOREIGN KEY (cardholder)
        REFERENCES users(id) ON DELETE CASCADE
);

--rollback DROP TABLE cards;