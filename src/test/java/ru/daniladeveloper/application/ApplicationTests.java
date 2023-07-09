package ru.daniladeveloper.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 Are you crazy? You are going to test HelloWorld with one switch!

 @link <a href = "https://spring.io/guides/gs/testing-web/"</a>
  * */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnRusMessage() throws Exception {
		String path = "/api/v1/get/hello/byLanguage?lang=ru";
		String expectedRusHelloWorld = "Привет, Мир!";
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + path,
				String.class)).contains(expectedRusHelloWorld);
	}


}
