package ru.daniladeveloper.application.domain.greeting;

import lombok.Builder;

@Builder
public class Hello {

    private String source;
    private String greeting;
    private String obj;
    private String emotion;
    private String punctuation;

    public static Hello byDefault() {
        return Hello.builder()
                .source("Server returns")
                .punctuation(", ")
                .greeting("Hello")
                .obj("World")
                .emotion("!")
                .build();
    }

    @Override
    public String toString() {
        return (source + ": " + greeting  + punctuation + obj + emotion)
                .replaceAll("null", " ");
    }
}
