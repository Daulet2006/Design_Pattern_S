package DandA.kz.command;

import DandA.kz.model.Order;
import DandA.kz.state.ReadyState;

public class DeliverOrderCommand implements Command {

    @Override
    public void execute(Order order) {
        order.setState(new ReadyState());
        System.out.println("Заказ " + order.getId() + " готов к доставке.");
    }
}
