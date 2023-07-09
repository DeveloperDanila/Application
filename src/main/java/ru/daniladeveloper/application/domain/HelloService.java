package ru.daniladeveloper.application.domain;

import org.springframework.stereotype.Service;
import ru.daniladeveloper.application.api.HelloResponseBody;

@Service
public class HelloService {

    public Hello getDefault() {
        return Hello.byDefault();
    }

    public Hello getRussian() {
        return Hello.builder()
                .source("Я")
                .greeting("Привет")
                .punctuation(", ")
                .obj("Мир")
                .emotion("!")
                .build();
    }


    public Hello getHelloByLanguage(String lang) {
        return switch (lang) {
            case "ru" -> getRussian();
            default -> getDefault();
        };
    }
}
