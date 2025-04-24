package Session_Project.DauletAndAltyndana.calculation;


import Session_Project.DauletAndAltyndana.model.Employee;

public class TaxCalculation implements Calculation {
    @Override
    public double calculate(Employee employee) {
        double taxRate = 0.2; // Налог 20%
        return employee.getBaseSalary() * taxRate;
    }
}