package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class User {
    @PositiveOrZero
    private int id;
    @Email
    @NotEmpty
    private String email;
    @NotNull
    @NotBlank
    @Pattern(regexp = ".*\\s.*")
    private String login;
    private String name;
    @PastOrPresent
    @NotNull
    private LocalDate birthday;
}