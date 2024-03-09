package com.basicauth.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "security.enabled=true" })
@AutoConfigureMockMvc
public class BasicAuthTests {
	@Autowired
	private MockMvc mockMvc;


	@Autowired
	TestRestTemplate template;

	@Test
	void indexAccessTest() throws Exception {
		ResponseEntity<String> res = template.withBasicAuth("abc", "def").exchange("/index", HttpMethod.GET, null,
				String.class);
		Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), res.getStatusCode().value());

	}

	@Test
	void shouldBeForbiddenWithIncorrectRoleTest() throws Exception {

		ResponseEntity<String> res = template.withBasicAuth("def@gmail.com", "pwd123").exchange("/admin-page",
				HttpMethod.GET, null, String.class);
		Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), res.getStatusCode().value());

	}

	@Test
	@WithMockUser(roles = { "ADMIN" })
	void shouldBeAbleToAccessAdminPageWithValidCredentialsTest() throws Exception {

		mockMvc.perform(get("/admin-page")).andExpect(status().isOk());

	}
}