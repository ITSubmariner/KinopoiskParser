package com.pet.kinopoiskparser.repository;

import com.pet.kinopoiskparser.domain.Kinopoisk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KinopoiskRepo extends JpaRepository<Kinopoisk, Long> {
    List<Kinopoisk> findTop10ByDateOrderByPositionAsc(LocalDate date);
}
