package Session_Project.DauletAndAltyndana.calculation;

import Session_Project.DauletAndAltyndana.model.Employee;

public class BonusCalculation implements Calculation {
    private Calculation baseCalculation; // Базовый расчет

    public BonusCalculation(Calculation baseCalculation) {
        this.baseCalculation = baseCalculation;
    }

    @Override
    public double calculate(Employee employee) {
        double bonusRate = 0.1;
        Double baseSalary = employee.getBaseSalary();
        return baseCalculation.calculate(employee) + (baseSalary * bonusRate);
    }
}
