package ru.daniladeveloper.application.api.greeting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.daniladeveloper.application.app.HelloDto;

@Getter
@AllArgsConstructor
public class HelloResponseBody {

    private String value;


    public static HelloResponseBody from(HelloDto hello) {
        return new HelloResponseBody(hello.getValue());
    }
}
