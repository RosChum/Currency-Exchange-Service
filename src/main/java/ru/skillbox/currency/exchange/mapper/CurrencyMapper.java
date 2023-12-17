package ru.skillbox.currency.exchange.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.ShortCurrencyDto;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.xmlEntity.Valute;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyDto convertToDto(Currency currency);

    Currency convertToEntity(CurrencyDto currencyDto);

    ShortCurrencyDto convertToShortDto(Currency currency);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "value", expression = "java(Double.parseDouble(valute.getValue().replace(\",\",\".\")))")
    @Mapping(target = "isoNumCode", source = "numCode")
    @Mapping(target = "isoTextCode", source = "charCode")
    Currency convertFromValuteToEntity(Valute valute);
}
