package net.javaguides.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.service.CoordinatorService;
import net.javaguides.springboot.web.dto.CoordinatorRegistrationDto;

//controller to register coordinator
@Controller
@RequestMapping(value = "/registration1")
public class CoordinatorRegistrationController {

	private CoordinatorService coordinatorService;

	public CoordinatorRegistrationController(CoordinatorService coordinatorService) {
		super();
		this.coordinatorService = coordinatorService;
	}
	
	@ModelAttribute("coordinator")
    public CoordinatorRegistrationDto userRegistrationDto() {
        return new CoordinatorRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "coordinatorregistration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("coordinator") CoordinatorRegistrationDto registrationDto) {
		coordinatorService.save(registrationDto);
		return "redirect:/registration1?success";
	}
}
