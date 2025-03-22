package DandA.kz.state;

import DandA.kz.model.Order;
import DandA.kz.model.OrderStatus;

public class ReadyState implements OrderState {

    @Override
    public void next(Order order) {
        order.setStatus(OrderStatus.DELIVERED);
        System.out.println("Заказ уже доставлен.");
    }

    @Override
    public void previous(Order order) {
        order.setStatus(OrderStatus.PREPARING);
        order.setState(new PreparingState());
    }

    @Override
    public String getStatus() {
        return "READY";
    }
}
