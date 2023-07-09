package ru.daniladeveloper.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

/**
 * Prints Hello World depends on locale
 * GoogleHelp:
 * @link <a href = https://howtodoinjava.com/java/date-time/how-to-get-current-user-locale-in-java/></a>
 * @link <a href = https://www.baeldung.com/spring-boot-console-app/></a>
 */
@SpringBootApplication
public class Application implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var currentLocale = Locale.getDefault();
		var currentLanguage = currentLocale.getLanguage();
		switch(currentLanguage) {
			case "en" -> System.out.println("Hello, World!");
			case "ru" -> System.out.println("Привет, Мир!");
		}
	}
}
