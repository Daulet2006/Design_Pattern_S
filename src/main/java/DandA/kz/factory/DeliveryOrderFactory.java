package DandA.kz.factory;

import DandA.kz.model.Order;
import DandA.kz.model.OrderStatus;

import java.time.LocalDateTime;

public class DeliveryOrderFactory extends OrderFactory {

    @Override
    public Order createOrder(String customerName, String contactInfo) {
        Order order = Order.builder()
                .customerName(customerName)
                .contactInfo(contactInfo)  // Теперь контактная информация корректно устанавливается
                .orderType("Delivery")
                .status(OrderStatus.RECEIVED)
                .orderTime(LocalDateTime.now())
                .build();
        System.out.println("Создан заказ на доставку для клиента: " + customerName);
        return order;
    }
}
