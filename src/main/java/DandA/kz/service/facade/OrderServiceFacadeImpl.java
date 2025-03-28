package DandA.kz.service.facade;

import DandA.kz.model.Customer;
import DandA.kz.model.Order;
import DandA.kz.state.OrderState;
import DandA.kz.model.OrderStatus;
import DandA.kz.repository.CustomerRepository;
import DandA.kz.repository.OrderRepository;
import DandA.kz.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceFacadeImpl implements OrderServiceFacade {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        // Set the customer and state fields
        if (order.getCustomer() == null && order.getCustomerId() != null) {
            Customer customer = customerRepository.findById(order.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + order.getCustomerId()));
            order.setCustomer(customer);
        }

        if (order.getState() == null && order.getStateId() != null) {
            OrderState state = stateRepository.findById(order.getStateId())
                    .orElseThrow(() -> new RuntimeException("State not found with ID: " + order.getStateId()));
            order.setState(state);
        }

        // Set default values
        order.setOrderTime(LocalDateTime.now()); // Set the current time as the order time
        order.setStatus(OrderStatus.RECEIVED); // Set the initial status to RECEIVED

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrder(Long orderId, Order order) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        // Update the fields of the existing order
        existingOrder.setCustomerName(order.getCustomerName());
        existingOrder.setContactInfo(order.getContactInfo());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setOrderType(order.getOrderType());
        existingOrder.setOrderTime(order.getOrderTime());

        // Update customer and state if provided
        if (order.getCustomer() != null) {
            existingOrder.setCustomer(order.getCustomer());
        } else if (order.getCustomerId() != null) {
            Customer customer = customerRepository.findById(order.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + order.getCustomerId()));
            existingOrder.setCustomer(customer);
        }

        if (order.getState() != null) {
            existingOrder.setState(order.getState());
        } else if (order.getStateId() != null) {
            OrderState state = stateRepository.findById(order.getStateId())
                    .orElseThrow(() -> new RuntimeException("State not found with ID: " + order.getStateId()));
            existingOrder.setState(state);
        }

        return orderRepository.save(existingOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> findOrdersByCustomerName(String customerName) {
        return orderRepository.findByCustomerNameContainingIgnoreCase(customerName);
    }


    @Override
    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public String executeCommand(Long orderId, String command) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        switch (command.toUpperCase()) {
            case "CANCEL":
                order.setStatus(OrderStatus.CANCELED);
                orderRepository.save(order);
                return "Order cancelled successfully.";
            case "READY":
                order.setStatus(OrderStatus.RECEIVED);
                orderRepository.save(order);
                return "Order confirmed successfully.";
            default:
                throw new RuntimeException("Invalid command: " + command);
        }
    }

    // Other methods remain unchanged...
}