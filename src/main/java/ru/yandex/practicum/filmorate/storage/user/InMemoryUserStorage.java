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

    private int id = 0;

    @Override
    public User createUser(User user) {
        userValidation(user);
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
    public User getUserById(Long id) {
        if (!users.containsKey(id)) {
            throw new ValidationException("Attempt to reach non-existing user with id '" + id + "'");
        }
        return users.get(id);
    }

    @Override
    public User updateUser(User user) {
        userValidation(user);
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            log.info("Информация о пльзователе {} обновлена.", user.getId());
        } else {
            throw new ValidationException("Пользователя с id: " + user.getId() + " нет.");
        }
        return user;
    }

    private void userValidation(User user) {
        if (user.getId() == 0 || user.getId() < 0) {
            user.setId(++id);
            log.info("Некорректно указан id.");
        }
        if (user.getName() == null) {
            user.setName(user.getLogin());
            log.info("Имя для отображения может быть пустым — в таком случае будет использован логин.");
        }
    }
}
