package com.pet.kinopoiskparser.controller;

import com.pet.kinopoiskparser.domain.Kinopoisk;
import com.pet.kinopoiskparser.service.KinopoiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/kinopoisk")
public class KinopoiskController {

    private final KinopoiskService kinopoiskService;

    @Autowired
    public KinopoiskController(KinopoiskService kinopoiskService) {
        this.kinopoiskService = kinopoiskService;
    }

    @GetMapping()
    public List<Kinopoisk> getTop10(
            @RequestParam(name = "date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        /*model.addAttribute("list", kinopoiskService.getTopByDay(date));
        model.addAttribute("date", date == null ? LocalDate.now() : date);
        return "index";*/
        return kinopoiskService.getTopByDay(date);
    }

}
