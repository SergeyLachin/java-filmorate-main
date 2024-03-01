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

@Slf4j
@RestController
public class FilmController {
    private final HashMap<Integer, Film> films = new HashMap<>();
    private int id = 0;

    @ResponseBody
    @PostMapping(value = "/films")
    public Film create(@Valid @RequestBody Film film) throws ValidationException {
        filmValidation(film);
        if (film.getId() <= 0) {
            film.setId(++id);
            log.info("Пустой идентификатор фильма.");
        }

        films.put(film.getId(), film);
        log.info("Фильм" + film.getName() + "добавлен в коллекцию.");
        return film;
    }

    @ResponseBody
    @GetMapping("/films")
    public List<Film> getFilms() {
        log.info("Список фильмов получен");
        return new ArrayList<>(films.values());
    }

    @ResponseBody
    @PutMapping("/films")
    public Film update(@Valid @RequestBody Film film) throws ValidationException {
        filmValidation(film);
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            log.info("Информация о фильме " + film.getName() + " обновлена.");
        } else {
            throw new ValidationException("Такого фильма в коллекции не существует.");
        }
        return film;
    }

    private void filmValidation(Film film) throws ValidationException {
        if (film.getReleaseDate() == null ||
                film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Дата релиза — не раньше 28 декабря 1895 года.");
        }
        if (film.getName().isEmpty() || film.getName().isBlank()) {
            throw new ValidationException("Название не может быть пустым");
        }
        if (film.getDuration() <= 0) {
            throw new ValidationException("Продолжительность фильма должна быть положительной.");
        }
        if (film.getDescription().length() > 200) {
            throw new ValidationException("Максимальная длина описания — 200 символов");
        }
        if (film.getDescription().isEmpty()) {
            throw new ValidationException("Описание фильма не заполнено");
        }
    }
}