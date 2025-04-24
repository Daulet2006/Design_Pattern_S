package Session_Project.DauletAndAltyndana.controller;

import Session_Project.DauletAndAltyndana.model.Employee;
import Session_Project.DauletAndAltyndana.model.Department;
import Session_Project.DauletAndAltyndana.service.EmployeeService;
import Session_Project.DauletAndAltyndana.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @GetMapping({"", "/"})
    public String index() {
        return "redirect:/employees/list"; // Redirect to the list view
    }

    @GetMapping("/{id}")
    public String getEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            return "error"; // Or a different error page if the employee is not found
        }
        double salary = employeeService.calculate(employee);

        model.addAttribute("employee", employee);
        model.addAttribute("salary", salary);

        return "employee-details";
    }


    @GetMapping("/group-view")
    public String getGroupCalculationView(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        double totalGroupCalculation = employeeService.calculateForGroup(employees);
        model.addAttribute("totalGroupSalary", totalGroupCalculation);
        return "group-salary";
    }

    @GetMapping("/group")
    @ResponseBody
    public String getGroupCalculation() {
        List<Employee> employees = employeeService.getAllEmployees();
        double totalGroupCalculation = employeeService.calculateForGroup(employees);
        return "Total Group Salary: " + totalGroupCalculation;
    }
    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public String handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
            // Log the error and return a user-friendly page
            return "error"; // You can create an error.html template for this
        }
    }

    @GetMapping("/list")
    public String listEmployees(@RequestParam(value = "search", required = false) String search,
                                @RequestParam(value = "position", required = false) String position,
                                Model model) {
        List<Employee> employees;
        if (search != null && !search.isEmpty()) {
            employees = employeeService.searchByName(search);
        } else if (position != null && !position.isEmpty()) {
            employees = employeeService.searchByPosition(position);
        } else {
            employees = employeeService.getAllEmployees();
        }
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    @GetMapping("/create")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employee-form";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Employee employee, @RequestParam Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId).orElse(null);
        employee.setDepartment(department);
        employeeService.saveEmployee(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employee-form";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee, @RequestParam Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId).orElse(null);
        employee.setDepartment(department);
        employee.setId(id);
        employeeService.saveEmployee(employee);
        return "redirect:/employees/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/list";
    }
}
