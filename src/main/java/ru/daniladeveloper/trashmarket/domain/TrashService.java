package ru.daniladeveloper.trashmarket.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daniladeveloper.trashmarket.app.TrashAddingResult;
import ru.daniladeveloper.trashmarket.infrustructure.entity.TrashEntity;

@Service
@RequiredArgsConstructor
public class TrashService {

    private final TrashRepository trashRepository;
    private final PhotoRepository photoRepository;
    private final AuthorRepository authorRepository;
    public TrashAddingResult add(Trash trash) {
        TrashEntity entity = TrashEntity.from(trash);
        entity.setAuthor(authorRepository.getReferenceById(trash.getAuthorId()));
        entity.setPhoto(photoRepository.getReferenceById(trash.getPhotoId()));

        var result = trashRepository.save(entity);

        return new TrashAddingResult(result.getId());
    }
}
