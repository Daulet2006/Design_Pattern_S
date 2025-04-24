package Session_Project.DauletAndAltyndana.calculation;

import Session_Project.DauletAndAltyndana.model.Employee;
import Session_Project.DauletAndAltyndana.state.ContractEmployeeState;
import Session_Project.DauletAndAltyndana.state.PermanentEmployeeState;
import Session_Project.DauletAndAltyndana.state.RemoteEmployeeState;

public class CalculationFactory {
    public static Calculation createCalculation(String type) {
        // Используем существующие классы State для определения логики расчета.
        // Примечание: Текущие классы State реализуют только calculateExtra.
        // Для полной интеграции может потребоваться рефакторинг Calculation и EmployeeState.
        // Пока что, для демонстрации, будем возвращать базовый расчет налога,
        // так как State классы не реализуют полный Calculation интерфейс.
        // В идеале, EmployeeState должен либо расширять Calculation, либо Calculation
        // должен использовать EmployeeState для определения дополнительных расчетов.

        // TODO: Рефакторинг для полной интеграции State Pattern в Calculation

        switch (type) {
            case "Permanent":
                // Логика для Permanent может включать бонус и налог, как было раньше.
                // Используем старую логику пока State не интегрирован полностью.
                // PermanentEmployeeState state = new PermanentEmployeeState();
                return new BonusCalculation(new TaxCalculation()); // Возвращаем старую логику

            case "Contract":
                // ContractEmployeeState state = new ContractEmployeeState();
                return new TaxCalculation(); // Возвращаем старую логику

            case "Remote":
                // RemoteEmployeeState state = new RemoteEmployeeState();
                return new TaxCalculation(); // Возвращаем старую логику

            default:
                throw new IllegalArgumentException("Unknown employee type: " + type);
        }
    }

    // Пример того, как можно было бы использовать State, если бы он реализовывал Calculation:
    /*
    public static Calculation createCalculationUsingState(String type) {
        switch (type) {
            case "Permanent":
                 // Предполагаем, что PermanentEmployeeState реализует Calculation
                 // return new PermanentEmployeeState();
                 // Или если State используется для модификации базового расчета:
                 return new StateBasedCalculation(new PermanentEmployeeState());
            case "Contract":
                 // return new ContractEmployeeState();
                 return new StateBasedCalculation(new ContractEmployeeState());
            case "Remote":
                 // return new RemoteEmployeeState();
                 return new StateBasedCalculation(new RemoteEmployeeState());
            default:
                throw new IllegalArgumentException("Unknown employee type: " + type);
        }
    }
    */

    // Вспомогательный класс для примера (не используется сейчас)
    /*
    private static class StateBasedCalculation implements Calculation {
        private Session_Project.DauletAndAltyndana.state.EmployeeState state;

        public StateBasedCalculation(Session_Project.DauletAndAltyndana.state.EmployeeState state) {
            this.state = state;
        }

        @Override
        public double calculate(Employee employee) {
            // Базовый расчет + дополнительные расчеты из состояния
            double baseCalc = employee.getBaseSalary(); // Пример базового расчета
            double extra = state.calculateExtra(employee);
            // Логика комбинирования базового расчета и 'extra' может быть сложнее
            return baseCalc - extra; // Пример: ЗП минус налог из состояния
        }
    }
    */
}
