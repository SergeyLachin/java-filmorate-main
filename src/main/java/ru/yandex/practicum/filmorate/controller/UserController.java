package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {
    private final HashMap<Integer, User> users = new HashMap<>();
    private int id = 0;

    @ResponseBody
    @PostMapping
    public User create(@Valid @RequestBody User user) throws ValidationException {
        userValidation(user);
        if (user.getName() == null) {
            user.setName(user.getLogin());
            log.info("Имя для отображения может быть пустым — в таком случае будет использован логин.");
        }
        users.put(user.getId(), user);
        log.info("Создан ползователь с id: " +  user.getId());
        return user;
    }

    @ResponseBody
    @GetMapping
    public List<User> getUsers() {
        log.info("Список пользователей получен");
        return new ArrayList<>(users.values());
    }

    @ResponseBody
    @PutMapping
    public User update(@Valid @RequestBody User user) throws ValidationException {
        userValidation(user);
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            log.info("Информация о пльзователе " + user.getId() + " обновлена.");
        } else {
            throw new ValidationException("Пользователя с id: " + user.getId() + " нет.");
        }
        return user;
    }

    private void userValidation(User user) throws ValidationException {
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Дата рождения не может быть в будущем.");
        }
        if (user.getEmail() == null || user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            throw new ValidationException("Электронная почта не может быть пустой и должна содержать символ @.");
        }
        if (user.getId() == 0 || user.getId() < 0) {
            user.setId(++id);
            log.info("Некорректно указа id.");
        }
        if (user.getLogin().isBlank() || user.getLogin().isEmpty()) {
            throw new ValidationException("Логин не может быть пустым и содержать пробелы.");
        }
    }
}