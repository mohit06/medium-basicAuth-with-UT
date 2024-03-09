package com.basicauth.app;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.basicauth.app.entity.UserProfile;
import com.basicauth.app.service.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class RegistrationTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	private ObjectMapper mapper = new ObjectMapper();
	
	@MockBean
	RegistrationService registerService;

	private UserProfile profile;
	private String jsonReq;

	@BeforeEach
	void setup() throws JsonProcessingException {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		profile = new UserProfile();
		profile.setEmail("abcd@gmail.com");
		profile.setId(3L);
		profile.setName("TEST");
		profile.setPassword("password");
		jsonReq = mapper.writeValueAsString(profile);
		
		when(registerService.registerUser(profile)).thenReturn(true);
	}

	@Test
	void registerUserTest() throws Exception {
		MvcResult res = mockMvc
				.perform(post("/register").content(jsonReq).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String response = res.getResponse().getContentAsString();
		
		
		
		Assertions.assertEquals("User created successfully", response);

	}

}
