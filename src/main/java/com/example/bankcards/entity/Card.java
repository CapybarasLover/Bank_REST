package com.example.bankcards.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity(name="cards")
public class Card {
    public Card(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length=19, unique=true, nullable=false)
    String card_number;

    @ManyToOne()
    @JoinColumn(name = "cardholder")
    User cardholder;

    @Column(nullable=false)
    Date valid_date;

    @Column(nullable=false)
    @Enumerated(value = EnumType.STRING)
    Status status;

    @Column(nullable=false)
    BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public User getCardholder() {
        return cardholder;
    }

    public void setCardholder(User cardholder) {
        this.cardholder = cardholder;
    }

    public Date getValid_date() {
        return valid_date;
    }

    public void setValid_date(Date valid_date) {
        this.valid_date = valid_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
