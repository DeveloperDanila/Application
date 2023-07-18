package ru.daniladeveloper.trashmarket.api.get;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.daniladeveloper.trashmarket.app.TrashApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/get")
@RequiredArgsConstructor
public class GetTrashController {

    private final TrashApplicationService trashApplicationService;

    @GetMapping("/trash")
    public ResponseEntity<List<TrashResponseBody>> all() {
        return ResponseEntity.ok(trashApplicationService.getAll());
    }
}
