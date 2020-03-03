package com.pet.kinopoiskparser.service;

import com.pet.kinopoiskparser.domain.Kinopoisk;

import java.time.LocalDate;
import java.util.List;

public interface KinopoiskService {
    List<Kinopoisk> getTopByDay(LocalDate date);
}
