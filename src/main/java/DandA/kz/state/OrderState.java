package DandA.kz.state;


import DandA.kz.model.Order;

public interface OrderState {
    void next(Order order);
    void previous(Order order);
    String getStatus();
}
