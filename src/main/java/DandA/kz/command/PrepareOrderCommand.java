package DandA.kz.command;

import DandA.kz.command.Command;
import DandA.kz.model.Order;
import DandA.kz.state.PreparingState;

public class PrepareOrderCommand implements Command {

    @Override
    public void execute(Order order) {
        order.setState(new PreparingState());
        System.out.println("Заказ " + order.getId() + " готовится.");
    }
}
