package com.pet.kinopoiskparser.service.impl;

import com.pet.kinopoiskparser.domain.Kinopoisk;
import com.pet.kinopoiskparser.repository.KinopoiskRepo;
import com.pet.kinopoiskparser.service.KinopoiskParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class KinopoiskParserImpl implements KinopoiskParser {
    private final String path;
    private final KinopoiskRepo kinopoiskRepo;

    public KinopoiskParserImpl(
            @Value("${kinopoisk.path}") String path,
            @Autowired KinopoiskRepo kinopoiskRepo) {
        this.path = path;
        this.kinopoiskRepo = kinopoiskRepo;
    }

    @Override
    @PostConstruct
//    @Scheduled(cron = "0 0 0 * * *")
    public void parse() throws IOException {
        Document doc = Jsoup.connect(path).get();
        List<Kinopoisk> list = new ArrayList<>();
        for (int i = 1; i <= 250; i++) {
            Element element = doc.getElementById("top250_place_" + i);
            list.add(buildItem(element));
        }
        kinopoiskRepo.saveAll(list);
    }

    private Kinopoisk buildItem(Element element) {
        Kinopoisk kinopoisk = new Kinopoisk();
        Elements links = element.select("a");
        String nameAndYear = links.get(1).html();
        Element span = element.getElementsByAttributeValue("style", "color: #777").first();
        kinopoisk.setPosition(Short.parseShort(links.first().attr("name")));
        kinopoisk.setName(nameAndYear.substring(0, nameAndYear.indexOf("(")-1));
        kinopoisk.setYear(Short.parseShort(nameAndYear.substring(nameAndYear.indexOf("(")+1, nameAndYear.indexOf(")"))));
        kinopoisk.setRating(Float.parseFloat(links.get(2).html()));
        kinopoisk.setVotes(Integer.parseInt(span.html().substring(1, span.html().length()-1).replace("&nbsp;", "")));
        kinopoisk.setDate(LocalDate.now());
        return kinopoisk;
    }
}
