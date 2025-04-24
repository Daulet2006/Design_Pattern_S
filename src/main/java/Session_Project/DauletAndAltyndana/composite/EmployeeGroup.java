package Session_Project.DauletAndAltyndana.composite;

import Session_Project.DauletAndAltyndana.calculation.Calculation;
import Session_Project.DauletAndAltyndana.model.Employee;
import java.util.List;

public class EmployeeGroup implements Calculation {
    private List<Employee> employees;
    public EmployeeGroup(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public double calculate(Employee employee) {
        return employees.stream()
                .mapToDouble(e -> e.getBaseSalary())
                .sum(); // Возвращаем общую сумму расчёта зарплаты (можно изменить логику)
    }
}
