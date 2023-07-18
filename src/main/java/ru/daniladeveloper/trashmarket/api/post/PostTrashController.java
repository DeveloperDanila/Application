package ru.daniladeveloper.trashmarket.api.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.daniladeveloper.trashmarket.app.TrashApplicationService;

@RestController
@RequestMapping("/api/v1/add")
@RequiredArgsConstructor
public class PostTrashController {

    private final TrashApplicationService trashApplicationService;

    @PostMapping("/trash")
    public ResponseEntity<?> addTrash(@RequestBody TrashRequestBody body) {
        var result = trashApplicationService.add(body);
        return result.getId()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
