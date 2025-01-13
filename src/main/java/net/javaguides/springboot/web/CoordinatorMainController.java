package net.javaguides.springboot.web;

import net.javaguides.springboot.model.Coordinator;
import net.javaguides.springboot.service.CoordinatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//controller to fetch information of coordinator at particular page or mapping
@Controller
public class CoordinatorMainController {

    private CoordinatorService coordinatorService;

    public CoordinatorMainController(CoordinatorService coordinatorService) {
        super();
        this.coordinatorService = coordinatorService;
    }

    @GetMapping("/admindashboard")
    public String showAdminDashboard(Model model) {
        model.addAttribute("coordinators", coordinatorService.getAllCoordinators());
        return "AdminDashboard";
    }
    @PostMapping("/coordinatorsecond")
    public String listUsers2(Model model) {
        return "redirect:/admindashboard";
    }

    @PostMapping("/adminmessage")
    public String saveNewCoordinator(@ModelAttribute("coordinators") Coordinator coordinator) {
        coordinatorService.saveCoordinator(coordinator);
        return "AdminMessage";
    }
    @GetMapping("/coordinator/new")
    public String createFacultyForm1(Model model) {
        // create student object to hold student form data
        Coordinator coordinator = new Coordinator();
        model.addAttribute("coordinators", coordinator);
        return "create_coordinator";
    }

    @GetMapping("/coordinator/{id}")
    public String deleteFaculty1(@PathVariable Long id, Model model) {
        // add faculty to model to display in the confirmation page
        model.addAttribute("coordinators", coordinatorService.getCoordinatorById(id));
        return "confirm_delete_coordinator";
    }
    @PostMapping("/coordinator/{id}/delete")
    public String confirmDeleteFaculty1(@PathVariable Long id, @RequestParam(value = "confirm", required = false) String confirm) {
        if ("yes".equals(confirm)) {
            // delete faculty record
            System.out.println(id+" in yes coordinator");
            coordinatorService.deleteCoordinatorById(id);
        }
        System.out.println(id+" not in yes coordinator");
        return "redirect:/admindashboard";
    }

/*if you want to update then use below controllers
@PostMapping("/coordinator/{id}")
public String updateFaculty1(@PathVariable Long id,
                             @ModelAttribute("coordinators") Coordinator coordinator,
                             Model model) {
    Coordinator existingFaculty = coordinatorService.getCoordinatorById(id);
    existingFaculty.setId(id);
    existingFaculty.setEmail(coordinator.getEmail());
    // save updated student object
    coordinatorService.updateCoordinator(existingFaculty);
    return "redirect:/admindashboard";
}
    @GetMapping("/coordinator/edit/{id}")
    public String editFacultyForm1(@PathVariable Long id, Model model) {
        model.addAttribute("coordinators", coordinatorService.getCoordinatorById(id));
        return "edit_faculty";
    }*/

}
