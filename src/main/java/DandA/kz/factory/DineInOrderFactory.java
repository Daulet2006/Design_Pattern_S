package DandA.kz.factory;

import DandA.kz.factory.OrderFactory;
import DandA.kz.model.Order;
import DandA.kz.model.OrderStatus;

import java.time.LocalDateTime;

public class DineInOrderFactory extends OrderFactory {

    @Override
    public Order createOrder(String customerName, String contactInfo) {
        Order order = Order.builder()
                .customerName(customerName)
                .contactInfo(contactInfo)
                .orderType("Dine-In")
                .status(OrderStatus.RECEIVED)
                .orderTime(LocalDateTime.now())
                .build();
        System.out.println("Создан заказ на месте для клиента: " + customerName);
        return order;
    }
}
