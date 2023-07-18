package ru.daniladeveloper.trashmarket.api.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrashRequestBody {
    private String title;
    private BigDecimal price;
    private String description;
    private String author;
    private Byte[] picture;
}
