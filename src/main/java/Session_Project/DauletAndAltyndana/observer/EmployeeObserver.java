package Session_Project.DauletAndAltyndana.observer;

import Session_Project.DauletAndAltyndana.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Session_Project.DauletAndAltyndana.model.Employee;
@Component
public class EmployeeObserver {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void notify(Employee employee) {
        employeeRepository.save(employee);
        System.out.println("Employee " + employee.getName() + " updated in database.");
    }
}
