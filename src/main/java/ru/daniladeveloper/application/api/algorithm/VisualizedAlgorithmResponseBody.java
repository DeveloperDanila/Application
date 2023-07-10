package ru.daniladeveloper.application.api.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VisualizedAlgorithmResponseBody {

    private String name;

    private AlgorithmType type;
}
