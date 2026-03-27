package com.example.bankcards.dto;

import com.example.bankcards.entity.Status;
import com.example.bankcards.entity.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CardDto {
    String cardNumber;
    User cardHolder;
    Date validCard;
    Status status;
    BigDecimal balance;
}
