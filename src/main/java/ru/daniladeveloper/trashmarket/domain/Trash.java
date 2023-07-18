package ru.daniladeveloper.trashmarket.domain;

import lombok.Builder;
import lombok.Data;
import ru.daniladeveloper.trashmarket.api.post.TrashRequestBody;

import java.math.BigDecimal;

@Data
@Builder
public class Trash {

    private String title;
    private BigDecimal price;
    private String description;
    private Long authorId;
    private Long photoId;

    public static Trash from(TrashRequestBody body, Long photoId, Long authorId) {
        return Trash.builder()
            .title(body.getTitle())
            .description(body.getDescription())
            .price(body.getPrice())
            .authorId(authorId)
            .photoId(photoId)
            .build();
    }
}
