package com.garcia.docker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

	private static final Logger log = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/about")
	public String getAbout() {
		log.info("Endpoint call:api/about");
		String result = "";
		try {
			//Use hostname instead to call the container name with exposed internal port
			result = restTemplate.getForObject(
				"http://data:8080/api/data/about", String.class);
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}

}
