import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import java.util.function.Consumer;


/**
    You are crazy? You are going to test HelloWorld with one switch!
   @see <a href="https://www.baeldung.com/run-shell-command-in-java></a>
   @link <a href = "https://www.baeldung.com/javadoc-linking-external-url></a>
   @link <a href = "https://www.baeldung.com/java-executor-service-tutorial"</a>
* */

public class TestHelloWorld {
    public static void main(String[] args) {
        checkHelloWorldMustBeOnRusWhenLocaleIsRussian();
    }
    
    public static void checkHelloWorldMustBeOnRusWhenLocaleIsRussian() {
        String expectedRusHelloWorld = "Привет, Мир!";
        String actual = getActualHelloWorld();
        
        if (!expectedRusHelloWorld.equalsIgnoreCase(actual)) {
            System.err.println("Test failed! Actual:" + actual);
        } else {
            System.out.println("Test passed!");
        }
    }

    private static String getActualHelloWorld() {
        StringBuilder sb = new StringBuilder();
        try(ExecutorService executor = Executors.newFixedThreadPool(1);) {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("java", "HelloWorld.java");
            builder.directory(new File(System.getProperty("user.dir")));

            Process process = builder.start();
            StreamProcessor streamProcessor =
                    new StreamProcessor(process.getInputStream(), (sb::append));
            Future<?> future = executor.submit(streamProcessor);

            int exitCode = process.waitFor();

            future.get();
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