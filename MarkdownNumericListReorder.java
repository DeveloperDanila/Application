import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MarkdownNumericListReorder {


    public MarkdownNumericListReorder() {
    }

    /***
     * String input = """
     *                 1. some first
     *                 3. some third
     *                 2. some second
     *                 """;
     *  String output = """
     *                 1. some first
     *                 2. some second
     *                 3. some third
     *                 """;
     * @param args
     */
    public static void main(String[] args) {

        String input = """
                1. some first
                3. some third
                2. some second
                """;
        MarkdownNumericListReorder helper = new MarkdownNumericListReorder();
        String result = helper.makeReorder(input);
        System.out.println(result);
    }

    private  String makeReorder(String input) {
        return Arrays.stream(input.split("\n"))
                .sorted()
                .reduce(((s, s2) -> s + "\n" + s2))
                .orElse("ERROR!");
    }
}
