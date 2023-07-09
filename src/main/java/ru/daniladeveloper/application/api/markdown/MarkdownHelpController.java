package ru.daniladeveloper.application.api.markdown;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.daniladeveloper.application.app.MarkdownHelperApplicationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/markdown/transform/")
public class MarkdownHelpController {

    private final MarkdownHelperApplicationService markdownService;

    @PostMapping("reorder")
    public String reorder(@RequestBody MarkdownRequestBody body) {
        return markdownService.reorder(body.getContent(), false);
    }
}
