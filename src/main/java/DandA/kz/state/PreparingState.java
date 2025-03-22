package DandA.kz.state;


import DandA.kz.model.Order;
import DandA.kz.model.OrderStatus;

public class PreparingState implements OrderState {

    @Override
    public void next(Order order) {
        order.setStatus(OrderStatus.valueOf("READY"));
        order.setState(new ReadyState());
    }

    @Override
    public void previous(Order order) {
        order.setStatus(OrderStatus.valueOf("RECEIVED"));
        order.setState(new ReceivedState());
    }

    @Override
    public String getStatus() {
        return "PREPARING";
    }
}
