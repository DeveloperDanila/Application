package ru.daniladeveloper.application.api.algorithm;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/visualize/algorithm")
public class VisualisationHomeController {

    @GetMapping("/")
    public List<VisualizedAlgorithmResponseBody> algos() {
        return List.of(new VisualizedAlgorithmResponseBody("BubbleSort"), new VisualizedAlgorithmResponseBody("QuickSort"));
    }


}
