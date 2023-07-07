import java.util.Locale;

/**
 * Prints Hello World depens on locale
 * GoogleHelp:
 * 1. https://howtodoinjava.com/java/date-time/how-to-get-current-user-locale-in-java/
 */

public class HelloWorld {
    public static void main(String[] args) {
        var currentLocale = Locale.getDefault();
        var currentLanguage = currentLocale.getLanguage();
        switch(currentLanguage) {
            case "en" -> System.out.println("Hello, world!");
            case "ru" -> System.out.println("Привет, мир!");
        }
    }
}