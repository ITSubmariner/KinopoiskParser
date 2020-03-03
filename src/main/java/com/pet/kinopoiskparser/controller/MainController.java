package com.pet.kinopoiskparser.controller;

import com.pet.kinopoiskparser.service.KinopoiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class MainController {
    private final KinopoiskService kinopoiskService;

    @Autowired
    public MainController(KinopoiskService kinopoiskService) {
        this.kinopoiskService = kinopoiskService;
    }

    @GetMapping("kinopoisk")
    public String getTop10(
            @RequestParam(name = "date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate date,
            Model model) {
        model.addAttribute("list", kinopoiskService.getTopByDay(date));
        model.addAttribute("date", date == null ? LocalDate.now() : date);
        return "index";
    }
}
