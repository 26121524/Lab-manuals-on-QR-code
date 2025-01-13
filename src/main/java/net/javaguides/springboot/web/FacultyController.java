package net.javaguides.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Faculty;
import net.javaguides.springboot.service.FacultyService;

//controller to fetch information of faculty(users) at particular page or mapping
@Controller
public class FacultyController {
	
	private FacultyService facultyService;

	public FacultyController(FacultyService facultyService) {
		super();
		this.facultyService = facultyService;
	}

	@GetMapping("/facultydashboard")
	public String listUsers(Model model) {
		model.addAttribute("faculty", facultyService.getAllFaculties());
		return "facultydashboard";
	}
	@GetMapping("/faculty")
	public String listUsers1(Model model) {
		model.addAttribute("faculty", facultyService.getAllFaculties());
		return "faculty";
	}
	@PostMapping("/facultysecond")
	public String listUsers2(Model model) {
		return "redirect:/faculty";
	}

	@GetMapping("/faculty/new")
	public String createFacultyForm(Model model) {
		// create object to hold faculty form data
		Faculty faculty = new Faculty();
		model.addAttribute("faculty", faculty);
		return "create_faculty";
	}

	@PostMapping("/coordinatormessage")
	public String saveFaculty(@ModelAttribute("faculty") Faculty faculty) {
		facultyService.saveFaculty(faculty);
		return "CoordinatorMessage";
	}
	
	@GetMapping("/faculty/edit/{id}")
	public String editFacultyForm(@PathVariable Long id, Model model) {
		model.addAttribute("faculty", facultyService.getFacultyById(id));
		return "edit_faculty";
	}

	@PostMapping("/faculty/{id}")
	public String updateFaculty(@PathVariable Long id,
			@ModelAttribute("faculty") Faculty faculty,
			Model model) {
		// get faculty from database by id
		Faculty existingFaculty = facultyService.getFacultyById(id);
		existingFaculty.setId(id);
		existingFaculty.setSubject(faculty.getSubject());
		existingFaculty.setFname(faculty.getFname());
		existingFaculty.setEmail(faculty.getEmail());
		// save updated object
		facultyService.updateFaculty(existingFaculty);
		return "redirect:/faculty";
	}
	// handler method to handle delete request
	@GetMapping("/faculty/{id}")
	public String deleteFaculty(@PathVariable Long id, Model model) {
	    // add faculty to model to display in the confirmation page
	    model.addAttribute("faculty", facultyService.getFacultyById(id));
	    return "confirm_delete_faculty";
	}
	@PostMapping("/faculty/{id}/delete")
	public String confirmDeleteFaculty(@PathVariable Long id, @RequestParam(value = "confirm", required = false) String confirm) {
	    if ("yes".equals(confirm)) {
	        // delete faculty record
			System.out.println(id+" in yes");
	    	facultyService.deleteFacultyById(id);
	    }
		System.out.println(id+" not in yes");
	    return "redirect:/faculty";
	}
	
}