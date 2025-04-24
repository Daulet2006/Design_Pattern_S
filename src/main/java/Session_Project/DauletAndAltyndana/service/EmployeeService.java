package Session_Project.DauletAndAltyndana.service;

import Session_Project.DauletAndAltyndana.calculation.Calculation;
import Session_Project.DauletAndAltyndana.calculation.CalculationFactory;
import Session_Project.DauletAndAltyndana.composite.EmployeeGroup;
import Session_Project.DauletAndAltyndana.model.Employee;
import Session_Project.DauletAndAltyndana.observer.EmployeeObserver;
import Session_Project.DauletAndAltyndana.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeObserver observer;

    /**
     * Расчёт показателей (зарплата, налог, бонус) для сотрудника.
     */
    public double calculate(Employee employee) {
        Calculation calculation = CalculationFactory.createCalculation(employee.getType());
        return calculation.calculate(employee);
    }

    /**
     * Загрузка сотрудника из БД.
     */
    public Employee getEmployee(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    /**
     * Рекурсивный расчёт для группы сотрудников.
     */
    public double calculateForGroup(List<Employee> employees) {
        EmployeeGroup group = new EmployeeGroup(employees);
        return group.calculate(null);
    }

    /**
     * Обновление после расчёта.
     */
    public void updateAfterCalculation(Employee employee) {
        observer.notify(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setName(updatedEmployee.getName());
        employee.setPosition(updatedEmployee.getPosition());
        employee.setBaseSalary(updatedEmployee.getBaseSalary());
        employee.setType(updatedEmployee.getType());
        employee.setDate(updatedEmployee.getDate());
        return repository.save(employee);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    public List<Employee> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Employee> searchByPosition(String position) {
        return repository.findByPositionContainingIgnoreCase(position);
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
