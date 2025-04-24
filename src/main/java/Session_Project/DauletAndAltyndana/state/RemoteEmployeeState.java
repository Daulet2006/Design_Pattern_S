package Session_Project.DauletAndAltyndana.state;

import Session_Project.DauletAndAltyndana.model.Employee;
public class RemoteEmployeeState implements EmployeeState {
    @Override
    public double calculateExtra(Employee employee) {
        return employee.getBaseSalary() * 0.15; // Налог: 15%
    }
}