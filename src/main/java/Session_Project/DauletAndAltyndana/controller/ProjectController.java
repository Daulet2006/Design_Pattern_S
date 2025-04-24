package Session_Project.DauletAndAltyndana.controller;

import Session_Project.DauletAndAltyndana.model.Project;
import Session_Project.DauletAndAltyndana.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "project-list";
    }

    @GetMapping("/add")
    public String showNewForm(Model model) {
        model.addAttribute("project", new Project());
        return "project-form";
    }

    @PostMapping("/add")
    public String addProject(@ModelAttribute Project project) {
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @PostMapping("/edit/{id}")
    public String editProject(@PathVariable Long id, @ModelAttribute Project project) {
        project.setId(id);
        projectService.saveProject(project);
        return "redirect:/projects";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id).orElse(null);
        if (project == null) {
            model.addAttribute("errorMessage", "Проект с таким ID не найден");
            return "error";
        }
        model.addAttribute("project", project);
        return "project-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }
}