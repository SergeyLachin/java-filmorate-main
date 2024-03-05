package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class User {
    private Set<Long> friends = new HashSet<>();
    @PositiveOrZero
    private int id;
    @Email
    @NotEmpty
    private String email;
    @NotNull
    @NotBlank
    @Pattern(regexp = "\\S+")
    private String login;
    private String name;
    @PastOrPresent
    @NotNull
    private LocalDate birthday;
}