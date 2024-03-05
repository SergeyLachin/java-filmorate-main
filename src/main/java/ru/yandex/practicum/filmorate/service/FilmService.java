package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilmService {

    Map<User, Film> topFilm = new HashMap<>();

    public void createLike () {

    }

    public void deleteLike () {

    }

    public List<Film> getTenPopFilm () {
        return null;
    }

//    добавление и удаление лайка
//    вывод 10 наиболее популярных фильмов по количеству лайков
//    Пусть пока каждый пользователь может поставить лайк фильму только один раз

}
