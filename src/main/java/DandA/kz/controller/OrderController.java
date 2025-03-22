package DandA.kz.controller;

import DandA.kz.model.Order;
import DandA.kz.model.OrderStatus;
import DandA.kz.service.facade.OrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderServiceFacade orderServiceFacade;

    // 📝 Получение всех заказов
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderServiceFacade.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // ➕ Создание нового заказа
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestParam String customerName,
            @RequestParam String contactInfo,
            @RequestParam String orderType) {
        Order createdOrder = orderServiceFacade.createOrder(customerName, contactInfo, orderType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // 🔄 Обновление заказа по ID
    // 🔄 Обновление заказа по ID
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable Long orderId,
            @RequestParam String status) {
        try {
            OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
            Order updatedOrder = orderServiceFacade.updateOrderStatus(orderId, newStatus);
            return ResponseEntity.ok(updatedOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    // ❌ Удаление заказа по ID
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderServiceFacade.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Получение заказа по ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderServiceFacade.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        return ResponseEntity.ok(order);
    }

    // 🚀 Выполнение команды (например, приготовить или доставить заказ)
    @PostMapping("/{orderId}/execute")
    public ResponseEntity<String> executeOrderCommand(
            @PathVariable Long orderId,
            @RequestParam String commandType) {
        String result = orderServiceFacade.executeCommand(orderId, commandType);
        return ResponseEntity.ok(result);
    }
}
