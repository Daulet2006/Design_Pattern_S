package DandA.kz.service.facade;

import DandA.kz.model.Order;
import DandA.kz.model.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderServiceFacade {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long orderId);
    Order createOrder(Order order);
    Order updateOrder(Long orderId, Order order);
    void deleteOrder(Long orderId);
    List<Order> findOrdersByCustomerName(String customerName);
    Order updateOrderStatus(Long orderId, OrderStatus status);
    String executeCommand(Long orderId, String command);

}