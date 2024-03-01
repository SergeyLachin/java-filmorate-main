package ru.yandex.practicum.filmorate.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class Film {
    @PositiveOrZero
    private int id;
    @NotNull
    private String name;
    @Size(min = 1, max = 200)
    private String description;
    private LocalDate releaseDate;
    @Positive
    private long duration;
}