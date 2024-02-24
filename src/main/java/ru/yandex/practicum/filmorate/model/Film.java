package ru.yandex.practicum.filmorate.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class Film {

    int id;
    @NotEmpty
    String name;
    @Max(200)
    String description;
    LocalDateTime releaseDate;
    @PositiveOrZero
    Duration duration;

}
