package ru.skillbox.currency.exchange.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShortCurrencyDto {

    private String name;

    private Double value;
}
