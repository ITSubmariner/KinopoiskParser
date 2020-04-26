package com.pet.kinopoiskparser.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
public class Kinopoisk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private short position;
    private float rating;
    private String name;
    private short year;
    private int votes;
    private LocalDate date;

    public Kinopoisk(short position, float rating, String name, short year, int votes, LocalDate date) {
        this.position = position;
        this.rating = rating;
        this.name = name;
        this.year = year;
        this.votes = votes;
        this.date = date;
    }
}
