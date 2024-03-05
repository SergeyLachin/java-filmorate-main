package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@Component
public class FilmController {

    FilmStorage filmStorage;
    FilmService filmService;

    @Autowired
    public FilmController (FilmStorage filmStorage, FilmService filmService) {
        this.filmStorage = filmStorage;
        this.filmService = filmService;
    }

    private final LocalDate birthdayFilm = LocalDate.of(1895, 12, 28);

    @PostMapping(value = "/films")
    public Film create(@Valid @RequestBody Film film) {
        filmValidation(film);
        return filmStorage.createFilm(film);
    }

    @GetMapping("/films")
    public List<Film> getFilms() {
        return filmStorage.getFilm();
    }

    @PutMapping("/films")
    public Film update(@Valid @RequestBody Film film) {
        filmValidation(film);
        return filmStorage.updateFilm(film);
    }

    private void filmValidation(Film film) {
        if (film.getReleaseDate() == null ||
                film.getReleaseDate().isBefore(birthdayFilm)) {
            throw new ValidationException("Дата релиза — не раньше 28 декабря 1895 года.");
        }
    }
}