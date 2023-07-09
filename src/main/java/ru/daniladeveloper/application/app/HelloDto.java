package ru.daniladeveloper.application.app;

import lombok.Builder;
import lombok.Getter;
import ru.daniladeveloper.application.domain.greeting.Hello;

@Builder
@Getter
public class HelloDto {

    String value;

    public static HelloDto from(Hello aDefault) {
        return HelloDto.builder()
                .value(aDefault.toString())
                .build();

    }
}
