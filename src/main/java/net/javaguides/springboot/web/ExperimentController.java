package net.javaguides.springboot.web;

import net.javaguides.springboot.model.ExperimentLink;
import net.javaguides.springboot.service.ExperimentLinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//controller to fetch information of experiments at particular page or mapping
@Controller
public class ExperimentController {

    private ExperimentLinkService experimentLinkService;

    public ExperimentController(ExperimentLinkService experimentLinkService) {
        super();
        this.experimentLinkService = experimentLinkService;
    }


    @GetMapping("/experiments/new")
    public String createFacultyForm(Model model) {
        // create student object to hold student form data
        ExperimentLink explink = new ExperimentLink();
        model.addAttribute("experiments", explink);
        return "create_experiment";
    }
    @PostMapping("/experiments/new")
    public String createFaculty(@ModelAttribute("experiments") ExperimentLink experimentLink) {
        experimentLinkService.saveExperimentLink(experimentLink);
        return "redirect:/experiments";
    }
    @PostMapping("/experiments")
    public String saveFaculty(@ModelAttribute("experiments") ExperimentLink experimentLink) {
        experimentLinkService.saveExperimentLink(experimentLink);
        return "experiment";
    }
    @PostMapping("/facultydashboard1")
    public String listUsers1(Model model) {
        model.addAttribute("experiments", experimentLinkService.getAllExperimentLinks());
        return "facultydashboard";
    }
}