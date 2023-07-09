package ru.daniladeveloper.application.api.greeting;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.daniladeveloper.application.app.HelloApplicationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/get/hello")
public class GetHelloController {

    private final HelloApplicationService helloService;

    @GetMapping()
    public HelloResponseBody defaultHello() {
        return HelloResponseBody.from(helloService.getDefaultHello());
    }

    @GetMapping("/byLanguage")
    public HelloResponseBody internationalHello(@RequestParam String lang) {
        return HelloResponseBody.from(helloService.getHelloByLanguage(lang));
    }

}
