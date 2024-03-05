package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage{

    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public User createUser(User user) {
        users.put(user.getId(), user);
        log.info("Создан ползователь с id: {}.",  user.getId());
        return user;
    }

    @Override
    public List<User> getUsers() {
        log.info("Список пользователей получен");
        return new ArrayList<>(users.values());
    }

    @Override
    public User updateUser(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            log.info("Информация о пльзователе {} обновлена.", user.getId());
        } else {
            throw new ValidationException("Пользователя с id: " + user.getId() + " нет.");
        }
        return user;
    }
}
