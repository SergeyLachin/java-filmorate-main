package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class FilmController {
    private final Map<Integer, Film> films = new HashMap<>();
    private int id = 0;
    private final LocalDate birthdayFilm = LocalDate.of(1895, 12, 28);

    @PostMapping(value = "/films")
    public Film create(@Valid @RequestBody Film film) {
        filmValidation(film);
        film.setId(++id);
        films.put(film.getId(), film);
        log.info("Фильм {} добавлен в коллекцию.",film.getName());
        id = film.getId();
        return film;
    }

    @GetMapping("/films")
    public List<Film> getFilms() {
        log.info("Список фильмов получен");
        return new ArrayList<>(films.values());
    }

    @PutMapping("/films")
    public Film update(@Valid @RequestBody Film film) {
        filmValidation(film);
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            log.info("Информация о фильме {} обновлена.", film.getName());
        } else {
            throw new ValidationException("Такого фильма в коллекции не существует.");
        }
        return film;
    }

    private void filmValidation(Film film) {
        if (film.getReleaseDate() == null ||
                film.getReleaseDate().isBefore(birthdayFilm)) {
            throw new ValidationException("Дата релиза — не раньше 28 декабря 1895 года.");
        }
    }
}