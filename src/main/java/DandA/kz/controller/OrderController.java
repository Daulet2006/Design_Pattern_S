package DandA.kz.controller;

import DandA.kz.dto.OrderDTO;
import DandA.kz.model.Order;
import DandA.kz.model.OrderStatus;
import DandA.kz.service.DtoMapper;
import DandA.kz.service.facade.OrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderServiceFacade orderServiceFacade;

    @Autowired
    private DtoMapper dtoMapper;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderServiceFacade.getAllOrders();
        List<OrderDTO> orderDTOs = orders.stream().map(dtoMapper::toOrderDTO).collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        Order order = orderServiceFacade.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        OrderDTO orderDTO = dtoMapper.toOrderDTO(order);
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = dtoMapper.toOrderEntity(orderDTO);
        Order createdOrder = orderServiceFacade.createOrder(order);
        OrderDTO createdOrderDTO = dtoMapper.toOrderDTO(createdOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDTO);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        Order order = dtoMapper.toOrderEntity(orderDTO);
        Order updatedOrder = orderServiceFacade.updateOrder(orderId, order);
        OrderDTO updatedOrderDTO = dtoMapper.toOrderDTO(updatedOrder);
        return ResponseEntity.ok(updatedOrderDTO);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderServiceFacade.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderDTO>> searchOrdersByCustomerName(@RequestParam String customerName) {
        List<Order> orders = orderServiceFacade.findOrdersByCustomerName(customerName);
        List<OrderDTO> orderDTOs = orders.stream()
                .map(dtoMapper::toOrderDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }


    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {
        Order updatedOrder = orderServiceFacade.updateOrderStatus(orderId, status);
        OrderDTO updatedOrderDTO = dtoMapper.toOrderDTO(updatedOrder);
        return ResponseEntity.ok(updatedOrderDTO);
    }

    @PostMapping("/{orderId}/execute")
    public ResponseEntity<String> executeOrderCommand(@PathVariable Long orderId, @RequestParam String command) {
        String result = orderServiceFacade.executeCommand(orderId, command);
        return ResponseEntity.ok(result);
    }
}