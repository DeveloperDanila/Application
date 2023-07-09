package ru.daniladeveloper.application.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.daniladeveloper.application.domain.greeting.HelloService;

@Service
@RequiredArgsConstructor
public class HelloApplicationService {

    private final HelloService service;

    public HelloDto getDefaultHello() {
        return HelloDto.from(service.getDefault());
    }

    public HelloDto getHelloByLanguage(String lang) {
        return HelloDto.from(service.getHelloByLanguage(lang));
    }
}
