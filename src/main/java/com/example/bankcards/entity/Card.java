package com.example.bankcards.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Entity(name="cards")
@Getter
@Setter
@RequiredArgsConstructor
public class Card {
    @Id
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
}
