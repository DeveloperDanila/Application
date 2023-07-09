package ru.daniladeveloper.application.domain.markdown;

import java.util.List;

public class MarkdownDocument  {
    List<Article> content;

    public MarkdownDocument(List<Article> content) {
        this.content = content;
    }

    public List<Article> getContent() {
        return content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Article article : content) {
            for (TextLine line : article) {
                sb.append(line.getValue());
            }
        }
        return sb.toString();
    }
}
