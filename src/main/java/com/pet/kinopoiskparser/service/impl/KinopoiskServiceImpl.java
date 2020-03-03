package com.pet.kinopoiskparser.service.impl;

import com.pet.kinopoiskparser.domain.Kinopoisk;
import com.pet.kinopoiskparser.repository.KinopoiskRepo;
import com.pet.kinopoiskparser.service.KinopoiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KinopoiskServiceImpl implements KinopoiskService {
    private final KinopoiskRepo kinopoiskRepo;

    @Autowired
    public KinopoiskServiceImpl(KinopoiskRepo kinopoiskRepo) {
        this.kinopoiskRepo = kinopoiskRepo;
    }

    @Override
    @Cacheable(value = "kinopoisk")
    public List<Kinopoisk> getTopByDay(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return kinopoiskRepo.findTop10ByDateOrderByPositionAsc(date);
    }
}
