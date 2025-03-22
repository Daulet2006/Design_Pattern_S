package DandA.kz.command;

import DandA.kz.model.Order;
import DandA.kz.model.OrderStatus;

public class CancelOrderCommand implements Command {

    @Override
    public void execute(Order order) {
        order.setStatus(OrderStatus.CANCELED);
        System.out.println("Заказ " + order.getId() + " отменён.");
    }
}
