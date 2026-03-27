package com.example.bankcards.service.card;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.UserDto;

import java.util.List;

public interface CardService {
// TODO описать методы сервиса карт
    List<CardDto> getUserCards(UserDto userDto);
    String createCard(UserDto userDto);
    String deleteCard(String cardholder, String cardNum);
    String deleteCardRequest(UserDto userDto, String cardNum);
}
