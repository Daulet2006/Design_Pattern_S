package DandA.kz.state;


import DandA.kz.model.Order;
import DandA.kz.model.OrderStatus;

public class ReceivedState implements OrderState {

    @Override
    public void next(Order order) {
        order.setStatus(OrderStatus.PREPARING);
        order.setState(new PreparingState());
    }

    @Override
    public void previous(Order order) {
        System.out.println("Заказ уже на начальной стадии.");
    }

    @Override
    public String getStatus() {
        return "RECEIVED";
    }
}
