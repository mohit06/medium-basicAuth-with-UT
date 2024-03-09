package com.basicauth.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/index")
	public String getIndex() {
		return "This is response to index api.";
	}
	
	@GetMapping("/admin-page")
	public String getAdmin() {
		return "This is response to admin api.";
	}
	
	@GetMapping("/contact")
	public String getContact() {
		return "This is response to contact api.";
	}

}
