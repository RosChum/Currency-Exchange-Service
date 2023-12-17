package ru.skillbox.currency.exchange.util;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.mapper.CurrencyMapper;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;
import ru.skillbox.currency.exchange.xmlEntity.ValCurs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateResourceFileAndDB {
    @Value("${currency-resource}")
    private String resourceFile;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private CurrencyMapper currencyMapper;

    private static final String URL = "https://www.cbr-xml-daily.ru/daily_utf8.xml";

    @Async
    @Scheduled(cron = "@hourly")
    public void updateFile() {
        try {
            Document document = Jsoup.connect(URL).ignoreHttpErrors(false).get();
            Path path = Paths.get(resourceFile);
            byte[] text = document.toString().getBytes();
            Files.write(path, text);
            updateDB();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void updateDB() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(new InputStreamReader
                    (new FileInputStream(resourceFile), StandardCharsets.UTF_8));
            List<Currency> currencyList = valCurs.getValuteList().stream()
                    .map(valute -> currencyMapper.convertFromValuteToEntity(valute)).collect(Collectors.toList());
            currencyRepository.saveAll(currencyList);
        } catch (JAXBException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
