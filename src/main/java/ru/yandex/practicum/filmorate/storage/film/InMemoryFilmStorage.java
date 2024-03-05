package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage{

    private final Map<Integer, Film> films = new HashMap<>();
    private int id = 0;

    @Override
    public Film updateFilm(Film film) {
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            log.info("Информация о фильме {} обновлена.", film.getName());
        } else {
            throw new ValidationException("Такого фильма в коллекции не существует.");
        }
        return film;
    }

    @Override
    public Film createFilm(Film film) {
        film.setId(++id);
        films.put(film.getId(), film);
        log.info("Фильм {} добавлен в коллекцию.",film.getName());
        id = film.getId();
        return film;
    }

    @Override
    public void deleteFilm() {

    }

    @Override
    public List<Film> getFilm() {
        log.info("Список фильмов получен");
        return new ArrayList<>(films.values());
    }
}
