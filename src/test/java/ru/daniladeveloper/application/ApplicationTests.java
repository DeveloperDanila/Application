package ru.daniladeveloper.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


/**
 Are you crazy? You are going to test src.main.java.ru.daniladeveloper.application.Application.HelloWorld with one switch!
 @see <a href="https://www.baeldung.com/run-shell-command-in-java></a>
 @link <a href = "https://www.baeldung.com/javadoc-linking-external-url></a>
 @link <a href = "https://www.baeldung.com/java-executor-service-tutorial"</a>
  * */
@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void checkHelloWorldMustBeOnRusWhenLocaleIsRussian() {
		String expectedRusHelloWorld = "Привет, Мир!";
		String actual = getActualHelloWorld();

		if (!actual.contains(expectedRusHelloWorld)) {
			Assertions.fail("Test failed! Actual: " + actual);
		} else {
			System.out.println("Test passed!");
		}
	}

	private static String getActualHelloWorld() {
		StringBuilder sb = new StringBuilder();
		try(ExecutorService executor = Executors.newFixedThreadPool(1);) {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command("./gradlew", "bootRun");
			builder.directory(new File(System.getProperty("user.dir")));

			Process process = builder.start();
			StreamProcessor streamProcessor =
					new StreamProcessor(process.getInputStream(), (sb::append));
			Future<?> future = executor.submit(streamProcessor);

			int exitCode = process.waitFor();

			future.get(30, TimeUnit.SECONDS);
			//assertDoesNotThrow(() -> future.get(10, TimeUnit.SECONDS));
			if (exitCode != 0) {
				return "Code was not executed";
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return "Exception in test";
		}
		return sb.toString();
	}

	private static class StreamProcessor implements Runnable {
		private InputStream inputStream;
		private Consumer<String> consumer;

		public StreamProcessor(InputStream inputStream, Consumer<String> consumer) {
			this.inputStream = inputStream;
			this.consumer = consumer;
		}

		@Override
		public void run() {
			new BufferedReader(new InputStreamReader(inputStream)).lines()
					.forEach(consumer);
		}
	}

}
