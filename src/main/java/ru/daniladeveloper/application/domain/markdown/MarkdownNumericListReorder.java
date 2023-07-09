package ru.daniladeveloper.application.domain.markdown;

import java.util.*;
import java.util.stream.Collectors;

public class MarkdownNumericListReorder {


    public MarkdownNumericListReorder() {
    }

    /*
    public static void main(String[] args) {

        String input = """
                1. some first
                3. some third
                2. some second
                """;
        MarkdownNumericListReorder helper = new MarkdownNumericListReorder();
        String result = helper.makeComplexReorder(input);
        System.out.println(result);
    }*/

    private String makeComplexReorder(String input) {
        var doc = prepareDocument(input);
        List<Article> articles = doc.getContent().stream()
                .map(this::getOrderedArticle).collect(Collectors.toList());
        return new MarkdownDocument(articles).toString();
    }

    private Article getOrderedArticle(Article disorderedArticle) {
        Integer order = 1;

        System.out.println();
        Article orderedArticle = new Article();
        for (TextLine line : disorderedArticle){
            if (line.isOrderedListItem()) {
                String content = line.getValue().split("\\.")[1];
                String newOrder = order + "." + content;
                orderedArticle.add(new TextLine(newOrder));
                order++;
            } else {
                orderedArticle.add(line);
            }
        }
        orderedArticle.stream()
                .filter(TextLine::isOrderedListItem)
                .forEach(System.out::println);
        return orderedArticle;
    }


    private String makeReorder(String input) {
        return Arrays.stream(input.split("\n"))
                .filter(this::startsWithNumber)
                .filter(this::isHasDot)
                .sorted(this::linesCompare)
                .reduce(((s, s2) -> s + "\n" + s2))
                .orElse("ERROR!");
    }

    private MarkdownDocument prepareDocument(String input) {
        Article article = new Article();
        Arrays.stream(input.split("\n"))
                .forEach(s-> {
                    var line = new TextLine(s + "\n");
                    article.add(line);
                    if (!startsWithNumber(s) && !isHasDot(s)) {
                        line.mark();
                    }
                });
        var articles = article.splitBy(TextLine::isContent);
        return new MarkdownDocument(articles);
    }

    private int linesCompare(String s, String s1) {
        Integer order1 = extractNumber(s);
        Integer order2 = extractNumber(s1);
        return order1.compareTo(order2);
    }

    private Integer extractNumber(String s) {
        int dotIndex = s.indexOf(".");
        String number = s.substring(0, dotIndex);

        return Integer.parseInt(number);
    }

    private boolean isHasDot(String s) {
        return s.contains(".");
    }

    private boolean startsWithNumber(String s) {
        return !s.isEmpty() && Character.isDigit(s.charAt(0));
    }

}
