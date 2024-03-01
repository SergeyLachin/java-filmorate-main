package ru.yandex.practicum.filmorate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmorateApplication {
	public static void main(String[] args) {
		SpringApplication.run(FilmorateApplication.class, args);
	}

}
//	    GET получает ресурсы;
//		POST создаёт ресурс;
//		PUT заменяет существующие данные или при их отсутствии создаёт новый ресурс;
//		PATCH используется для частичного обновления данных ресурса;
//		DELETE удаляет ресурс.