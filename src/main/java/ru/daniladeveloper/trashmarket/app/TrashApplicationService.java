package ru.daniladeveloper.trashmarket.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daniladeveloper.trashmarket.api.get.TrashResponseBody;
import ru.daniladeveloper.trashmarket.api.post.TrashRequestBody;
import ru.daniladeveloper.trashmarket.domain.*;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrashApplicationService {

    private final TrashService trashService;
    private final PhotoService photoService;
    private final AuthorService authorService;

    public List<TrashResponseBody> getAll() {
        return Collections.emptyList();
    }

    public TrashAddingResult add(TrashRequestBody body) {
        PhotoContent photoContent = PhotoContent.from(body.getPicture());

        Long photoId = photoService.savePhoto(photoContent);

        Long authorId = authorService.findOrCreateByName(body.getAuthor());

        return trashService.add(Trash.from(body, photoId, authorId));
    }
}
