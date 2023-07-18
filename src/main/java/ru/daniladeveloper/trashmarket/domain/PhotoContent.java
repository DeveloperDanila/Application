package ru.daniladeveloper.trashmarket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhotoContent {

    private Byte[] content;

    public static PhotoContent from(Byte[] picture) {
        return new PhotoContent(picture);
    }
}
