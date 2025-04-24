package Session_Project.DauletAndAltyndana.controller;

import Session_Project.DauletAndAltyndana.model.Department;
import Session_Project.DauletAndAltyndana.model.Employee;
import Session_Project.DauletAndAltyndana.service.DepartmentService;
import Session_Project.DauletAndAltyndana.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "department-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department-form";
    }

    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id).orElse(new Department());
        model.addAttribute("department", department);
        return "department-form";
    }

    @PostMapping("/edit/{id}")
    public String editDepartment(@PathVariable Long id, @ModelAttribute Department department) {
        department.setId(id);
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }

    @GetMapping("/{id}/employees")
    public String listEmployeesByDepartment(@PathVariable Long id, Model model) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        if (department.isPresent()) {
            List<Employee> employees = department.get().getEmployees();
            model.addAttribute("department", department.get());
            model.addAttribute("employees", employees);
            return "department-employees";
        } else {
            return "redirect:/departments";
        }
    }
}