package DandA.kz.command;

import DandA.kz.model.Order;
import DandA.kz.model.OrderStatus;

public class PrepareOrderCommand implements Command {

    @Override
    public void execute(Order order) {
        order.setStatus(OrderStatus.PREPARING);
        System.out.println("Заказ " + order.getId() + " готовится.");
    }
}