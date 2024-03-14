package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {

    Film updateFilm(Film film);

    Film createFilm(Film film);

    void deleteFilm();

    List<Film> getFilm();

    public Film getFilmById(Long id);
}
