package ru.daniladeveloper.trashmarket.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daniladeveloper.trashmarket.infrustructure.entity.AuthorEntity;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;


    public Long findOrCreateByName(String author) {
        return authorRepository.findByName(author).orElseGet(() -> {
            AuthorEntity entity = AuthorEntity.builder()
                .name(author)
                .build();
            return authorRepository.save(entity);
        }).getId();
    }
}
