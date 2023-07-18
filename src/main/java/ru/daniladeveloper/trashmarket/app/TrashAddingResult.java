package ru.daniladeveloper.trashmarket.app;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Optional;

@Builder
@AllArgsConstructor
public class TrashAddingResult {

    @Nullable
    private Long trashId;

    public Optional<Long> getId() {
        return Optional.ofNullable(trashId);
    }

}
