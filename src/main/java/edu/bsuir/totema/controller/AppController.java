package edu.bsuir.totema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/")
public class AppController {

	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}

	@RequestMapping(value = { "/products"}, method = RequestMethod.GET)
	public String productsPage() {
		return "products";
	}

	@RequestMapping(value = { "/contact"}, method = RequestMethod.GET)
	public String contactUsPage() {
		return "contact";
	}
}