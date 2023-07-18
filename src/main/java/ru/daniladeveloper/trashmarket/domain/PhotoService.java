package ru.daniladeveloper.trashmarket.domain;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import ru.daniladeveloper.trashmarket.infrustructure.entity.PhotoEntity;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;


    public Long savePhoto(PhotoContent content) {
          return  photoRepository.save(PhotoEntity.builder()
                    .content(ArrayUtils.toPrimitive(content.getContent()))
                .build()).getId();
    }

}
