package ru.daniladeveloper.application.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daniladeveloper.application.domain.markdown.MarkdownNumericListReorderService;

@Service
@RequiredArgsConstructor
public class MarkdownHelperApplicationService {

    private final MarkdownNumericListReorderService reorderService;

    public String reorder(String content, boolean whole) {
        if (!whole) {
            return reorderService.makeReorderByArticle(content);
        } else {
            return reorderService.makeWholeReorder(content);
        }
    }
}
