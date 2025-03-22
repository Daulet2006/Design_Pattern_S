package DandA.kz.service.facade;

import DandA.kz.model.Customer;
import DandA.kz.model.Order;
import DandA.kz.model.OrderStatus;
import DandA.kz.repository.CustomerRepository;
import DandA.kz.repository.OrderRepository;
import DandA.kz.repository.StrategyRepository;
import DandA.kz.strategy.OrderStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceFacade {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StrategyRepository strategyRepository;

    /**
     * Создать новый заказ
     */
    @Transactional
    public Order createOrder(String customerName, String contactInfo, String orderType) {
        Customer customer = customerRepository.findByName(customerName);
        if (customer == null) {
            customer = Customer.builder()
                    .name(customerName)
                    .contactInfo(contactInfo)
                    .build();
            customerRepository.save(customer);
        }

        Order order = Order.builder()
                .customerName(customerName)
                .status(OrderStatus.RECEIVED)
                .orderType(orderType)
                .orderTime(LocalDateTime.now())
                .customer(customer)
                .build();

        return orderRepository.save(order);
    }

    /**
     * Получить все заказы
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Найти заказ по ID
     */
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    /**
     * Изменить статус заказа
     */
    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(newStatus);
            return orderRepository.save(order);
        }
        throw new RuntimeException("Заказ с ID " + orderId + " не найден");
    }

    /**
     * Удалить заказ
     */
    @Transactional
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Заказ с ID " + id + " не найден");
        }
    }

    /**
     * Получить стратегию заказа
     */
    public OrderStrategy getOrderStrategy(Long strategyId) {
        return strategyRepository.findById(strategyId).orElseThrow(() ->
                new RuntimeException("Стратегия с ID " + strategyId + " не найдена"));
    }
    @Transactional
    public String executeCommand(Long orderId, String commandType) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            switch (commandType.toLowerCase()) {
                case "prepare":
                    order.setStatus(OrderStatus.PREPARING);
                    break;
                case "deliver":
                    order.setStatus(OrderStatus.DELIVERED);
                    break;
                case "cancel":
                    order.setStatus(OrderStatus.CANCELED);
                    break;
                default:
                    throw new RuntimeException("Неизвестная команда: " + commandType);
            }
            orderRepository.save(order);
            return "Команда выполнена: " + commandType;
        }
        throw new RuntimeException("Заказ с ID " + orderId + " не найден");
    }
}
