package Session_Project.DauletAndAltyndana.state;

import Session_Project.DauletAndAltyndana.model.Employee;
public class PermanentEmployeeState implements EmployeeState {
    @Override
    public double calculateExtra(Employee employee) {
        return employee.getBaseSalary() * 0.2; // Налог: 20%
    }
}