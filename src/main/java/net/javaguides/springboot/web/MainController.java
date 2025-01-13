package net.javaguides.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

//main controller - contains all general or main pages mappings
@Controller
public class MainController {
	@GetMapping("/login-faculty")
	public String  loginFaculty(){
		return "login.html";
	}

	@GetMapping("/coordinator")
	public String login1() {
		return "coordinatorlogin.html";
	}
    @RequestMapping("/")
    public String homePage(HttpServletRequest request) {
	if (request.isUserInRole("ROLE_ADMIN")) {
		return "Adminindex";
	} else if (request.isUserInRole("ROLE_USER")) {
		return "facultyindex";
	} else if (request.isUserInRole("ROLE_COORDINATOR")) {
		return "coordinatorindex";
	} else{
		return "error";
	}
}
	@GetMapping("/login")
	public String login() {
		return "Adminlogin";
	}
	@PostMapping("/")
	public String adminIndex() {
		return "Adminindex";
	}
	@GetMapping("/create-course")
	public String createCourse() {
		return "create-course";
	}

}
