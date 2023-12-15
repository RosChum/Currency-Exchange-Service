package ru.skillbox.currency.exchange.util;


import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLConnection;

@Slf4j
@Component
public class UpdateResourceAndDB {

    @Async
    @Scheduled(cron = "@hourly")
    public void updateFile() throws Exception {
        URLConnection url = new URL("https://www.cbr-xml-daily.ru/daily_utf8.xml").openConnection();
        String urls = "https://www.cbr-xml-daily.ru/daily_utf8.xml";
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
//        InputStream stream = url.getInputStream();
//        int i;
//        if ((i = stream.read()) > -1) {
//            log.info("trxt" + (char)i);
//        }
//
//        Document doc = docBuilder.parse(stream);
//       String xmlFileText = doc.getTextContent();

        Document document = Jsoup.connect(urls).ignoreHttpErrors(false).get();
     // дописать сохранние файла в ресурсах
    }

}
