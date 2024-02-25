package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.rmi.server.LogStream.log;

@RestController
@Slf4j
public class FilmController {
    LocalDateTime a = LocalDateTime.of(1895, 12, 28, 0, 0);
    List<Film> films = new ArrayList<>();

    @GetMapping("/films")
    public List<Film> getFilms() {
        return films;
    }

    @PostMapping(value = "/films")
    public Film create(@RequestBody Film film) throws ValidationException {
        if (film.getReleaseDate().isAfter(a)){
            films.add(film);
            log("Добавили новый фильм.");
        }else {
            throw new ValidationException("Неверная дата релиза");
        }
        return film;
    }

    @PutMapping(value = "/films")
    public Film update(@RequestBody Film film) throws ValidationException {
        if (film.getReleaseDate().isAfter(a)) {
            for (int i = 0; i < films.size(); i++) {
                if (film.getId() == i) {
                    films.add(i, film);
                    log("Фильм изменен.");
                }
            }
        }else {
            throw new ValidationException("Неверная дата релиза");
        }
        return film;
    }
}
