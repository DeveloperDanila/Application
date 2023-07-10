package ru.daniladeveloper.application.api.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VisualizedAlgorithmResponseBody {

    private String name;

    private AlgorithmType type;

    public VisualizedAlgorithmResponseBody(String name) {
        this.name = name;
        this.type = AlgorithmType.GENERAL;
    }
}
